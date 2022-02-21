package com.example.giph_y

data class Data(val images : Images, val title : String)

data class Images(val original: Original)

data class Original( val url : String)







