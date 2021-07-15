package com.mridwan.ecommerce.view.ui.detail_product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.mridwan.ecommerce.R
import com.mridwan.ecommerce.model.ProductHistory
import com.mridwan.ecommerce.view.utils.DBHelper

class DetailProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val imageProduct = findViewById<ImageView>(R.id.image_product)
        val price = findViewById<TextView>(R.id.price)
        val backButton = findViewById<ImageView>(R.id.btn_back)
        val shareButton = findViewById<ImageView>(R.id.btn_share)
        val fav = findViewById<ToggleButton>(R.id.fav)
        val buyButton = findViewById<Button>(R.id.btnBuy)
        val titleProduct = intent.getStringExtra("title")
        val descProduct = intent.getStringExtra("description")
        val priceProduct = intent.getStringExtra("price")
        val imageLinkProduct = intent.getStringExtra("image")
        var lovedProduct = intent.getIntExtra("loved",0)

        shareButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Buy $titleProduct on my ecommerce")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        backButton.setOnClickListener {
            onBackPressed()
        }

        buyButton.setOnClickListener {
            val dbHelper = DBHelper(this,null)
            val historyProduct = ProductHistory(
                titleProduct,
                descProduct,
                priceProduct,
                imageLinkProduct,
                lovedProduct
            )
            dbHelper.addProduct(historyProduct)
            Toast.makeText(this, titleProduct + "Added to purchased history", Toast.LENGTH_LONG).show()
        }

        title.text = titleProduct
        description.text = descProduct
        price.text = priceProduct
        Glide.with(this).load(imageLinkProduct).into(imageProduct)
        if (lovedProduct == 1){
            fav.isChecked = true
        }
    }
}