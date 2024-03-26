package com.dheril.productshopping

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val btnBuy: Button = findViewById(R.id.action_buy)
        btnBuy.setOnClickListener(this)
        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailPrice: TextView = findViewById(R.id.tv_detail_price)
        val tvDetailRating: TextView = findViewById(R.id.tv_detail_rating)
        val tvDetailDesc: TextView = findViewById(R.id.tv_detail_description)
        val tvDetailChip: TextView = findViewById(R.id.tv_detail_chip)
        val tvDetailSize: TextView = findViewById(R.id.tv_detail_size)
        val tvDetailStorage: TextView = findViewById(R.id.tv_detail_storage)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)

        val product = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Product>("DATA", Product::class.java)

        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Product>("DATA")

        }
        if (product != null) {
            tvDetailName.text = product.name
            tvDetailPrice.text = product.price
            tvDetailRating.text = product.rating
            tvDetailDesc.text = product.description
            tvDetailChip.text = product.chip
            tvDetailSize.text = product.size
            tvDetailStorage.text = product.storage
            imgPhoto.setImageResource(product.photo)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.action_buy ->{
                Toast.makeText(this, "Thank you for buying", Toast.LENGTH_SHORT).show()
            }
            R.id.action_share ->{
                val product = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Product>("DATA", Product::class.java)

                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Product>("DATA")

                }
                if (product != null) {
                    val shareText = "Check out this product: ${product.name}\n" + "Price: ${product.price}\n" + "Rating: ${product.rating}\n" + "Description: ${product.description.substring(0, 50)}..."
                    val intentToShare = Intent(Intent.ACTION_SEND)
                    intentToShare.type = "text/plain"
                    intentToShare.putExtra("DATA", shareText)
                    startActivity(Intent.createChooser(intentToShare, "Share with:"))
                }

            }
        }

    }
}