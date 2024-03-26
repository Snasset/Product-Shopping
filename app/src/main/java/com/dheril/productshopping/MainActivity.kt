package com.dheril.productshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvProduct: RecyclerView
    private val list = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvProduct = findViewById(R.id.rv_products)
        rvProduct.setHasFixedSize(true)
        list.addAll(getListProducts())
        showRecyclerList()
    }



    private fun getListProducts(): ArrayList<Product>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataPrice= resources.getStringArray(R.array.data_price)
        val dataChip = resources.getStringArray(R.array.data_spec_chip)
        val dataDisplay = resources.getStringArray(R.array.data_spec_display)
        val dataStorage = resources.getStringArray(R.array.data_spec_storage)
        val listProduct = ArrayList<Product>()
        for (i in dataName.indices) {
            val product = Product(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1),dataRating[i],dataPrice[i],dataChip[i],dataDisplay[i],dataStorage[i])
            listProduct.add(product)
        }
        return listProduct
    }

    private fun showRecyclerList() {
        rvProduct.layoutManager = LinearLayoutManager(this)
        val ListProductAdapter = ListProductAdapter(list)
        rvProduct.adapter = ListProductAdapter

        ListProductAdapter.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Product) {
                val intentToDetail = Intent(this@MainActivity, ProductDetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about_page -> {
                val IntentToProfile = Intent(this@MainActivity, AboutPageActivity::class.java)
                startActivity(IntentToProfile)
            }
        }
        return super.onOptionsItemSelected(item)
    }




}