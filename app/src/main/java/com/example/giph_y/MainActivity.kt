package com.example.giph_y



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giph_y.Adapter.LoadingStateAdapter
import com.example.giph_y.Adapter.MyAdapter
import com.example.giph_y.Adapter.PagingAdapter
import com.example.giph_y.Adapter.UserAdapter
import com.example.giph_y.databinding.ActivityMainBinding
import com.example.giph_y.ui.Gif
import com.example.giph_y.viewModel.GifViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapters: UserAdapter
    private  lateinit var pagingAdapter : PagingAdapter
    private lateinit var gifViewModel : GifViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initRecyclerView()
        lifecycleScope.launchWhenStarted {
            gifViewModel.getAllgif().collectLatest {response->
                binding.apply {
                    relist.isVisible = true
                    ProgressBar.isVisible = false
                }
                pagingAdapter.submitData(response)
            }
        }
        binding.tabLayout.addTab(tabLayout.newTab().setText("Trending"))
        binding.tabLayout.addTab(tabLayout.newTab().setText("Stickers"))
        binding.tabLayout.addTab(tabLayout.newTab().setText("Text"))
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager, tabLayout.tabCount)
        binding.viewPager.adapter = adapter
        binding. viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
            binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                    binding.tabLayout.setVisibility(View.GONE)
                binding.viewPager.setVisibility(View.GONE)
                val gifs = query?.let { SearchService.SearchInstance.getSearch(100, 20, it)}
                if(gifs != null) {
                    gifs.enqueue(object : Callback<Gif> {
                        override fun onFailure(call: Call<Gif>, t: Throwable) {
                            Log.d("ok", "error in fetching search gif", t)
                        }
                        override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                            val gifs = response.body()
                            if (gifs != null) {
                                Log.d("ok", gifs.toString())
                                adapters = UserAdapter(this@MainActivity, gifs.data)
                                binding.relist.adapter = adapters
                                binding.relist.layoutManager = LinearLayoutManager(this@MainActivity)
                            }
                        }
                    })
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val gifs = newText?.let { SearchService.SearchInstance.getSearch(50, 20, it) }
                if (gifs != null) {
                    gifs.enqueue(object : Callback<Gif> {
                        override fun onFailure(call: Call<Gif>, t: Throwable) {
                            Log.d("ok", "error in fetching search gif", t)
                        }
                        override fun onResponse(call: Call<Gif>, response: Response<Gif>) {
                            val gifs = response.body()
                            if (gifs != null) {
                                Log.d("ok", gifs.toString())
                                adapters = UserAdapter(this@MainActivity, gifs.data)
                                binding.relist.adapter = adapters
                                binding.relist.layoutManager = LinearLayoutManager(this@MainActivity)
                            }
                        }
                    })
                }
                return false
            }
        })
    }
     private fun initRecyclerView() {
         binding.apply {
             relist.apply {
                 setHasFixedSize(true)
                 layoutManager = LinearLayoutManager(this@MainActivity)
                 adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                     header = LoadingStateAdapter{pagingAdapter.retry()},
                     footer = LoadingStateAdapter{pagingAdapter.retry()}
                 )
             }
         }
     }
 }











