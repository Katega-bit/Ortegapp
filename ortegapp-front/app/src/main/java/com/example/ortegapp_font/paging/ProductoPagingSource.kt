package com.example.ortegapp_font.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.repository.ProductoRepository

class ProductoPagingSource(private val repository: ProductoRepository, private val token : String) : PagingSource<Int, Producto>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Producto> {

        return try {
            val page = params.key ?: 0
            val response = repository.getProduct(page, token)
            val data = response.body()?.content
            val responseData = mutableListOf<Producto>()
            responseData.addAll(data!!)

            LoadResult.Page(
                data = responseData,
                prevKey = if(page == 1 ) null else -1,
                nextKey = page.plus(1)
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Producto>): Int? {
        TODO("Not yet implemented")
    }


}