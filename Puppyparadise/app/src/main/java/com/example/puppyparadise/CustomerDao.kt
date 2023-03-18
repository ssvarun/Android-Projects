package com.example.puppyparadise

import androidx.room.*
import com.example.puppyparadise.relations.CustomerAndCart
import com.example.puppyparadise.relations.CustomerAndOrder
import com.example.puppyparadise.relations.PetAndCategory

@Dao
interface CustomerDao {
    //Customer Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertcustomer(customer: Customer)

    @Transaction
    @Query("Update customer_table SET Name=:name,Address=:address,MobileNumber=:number,Pincode=:pincode where customerid=:id")
    suspend fun updatecustomerdetails(id:Int,name:String,address:String,number:Int,pincode:Int)

    @Query("Select * from customer_table")
    suspend fun getallcustomerslist() : List<Customer>

    @Query("Delete from  customer_table where customerid=:customerId")
    suspend fun deletecustomerwithid(customerId: Int)

    @Query("DELETE FROM customer_table")
    suspend fun deleteallcustomers()

    @Query("Select Password from customer_table where Email=:email")
    suspend fun getpasswordofemail(email:String) : String

    @Query("Select * from customer_table where Email=:email")
    suspend fun getcustomerprofiledescrition(email:String) : Customer

    @Transaction
    @Query("Select customerid from customer_table where Email=:email")
    suspend fun returncustomerid(email: String):Int

    @Transaction
    @Query("Select * from customer_table where customerid =:cid")
    suspend fun getcustomerorderwithid(cid:Int) :List<CustomerAndOrder>

    @Transaction
    @Query("Select * from CUSTOMER_TABLE where customerid =:cid")
    suspend fun getcustomerandcartoftheid(cid:Int) : List<CustomerAndCart>

    @Query("Select * from customer_table where Email=:email")
    suspend fun checkemail(email: String) : Customer

    //Cart Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertcart(cart: Cart)

    @Query("SELECT EXISTS (SELECT * FROM cart_table WHERE customerid = :customerId)")
    suspend fun iscartexists(customerId: Int) : Boolean

    @Query("Select cartid from cart_table where customerid=:customerid")
    suspend fun getcartid(customerid:Int) : Int

    @Query("Delete from cart_table where customerid=:customerId")
    suspend fun deletecartofcustomer(customerId: Int)


    //Category Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertcategory(category: Category)

    @Transaction
    @Query("Select * from category_table where categoryid =:categoryid")
    suspend fun getpetsofcategorywithid(categoryid:Int) :List<PetAndCategory>



    //Pet Details table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertpetdetails(petDetails: PetDetails)

    @Transaction
    @Query("Select * from petdetails_table")
    suspend fun getallpetslist() :List<PetDetails>

    @Transaction
    @Query("Select * from petdetails_table where Petid=:petid")
    suspend fun getpetdetailswithid(petid:Int) : PetDetails




    //Order table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertorder(order: Order)

    @Transaction
    @Query("Select * from order_table where customerid=:customerId")
    suspend fun getorderdetailseithid(customerId:Int) : List<Order>

    @Query("Delete from order_table where customerid=:customerId")
    suspend fun deleteordersofcustomer(customerId: Int)


    // Cartitem table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertcartitem(cartItems: CartItems)

    @Transaction
    @Query("Select * from cartitem_table where cartid=:cartId")
    suspend fun getallcartitems(cartId:Int) : List<CartItems>

    @Transaction
    @Query("Delete from cartitem_table where cartid=:cartId")
    suspend fun deleteallwithcartid(cartId:Int)

    @Query("Delete from cartitem_table where cartid=:cartId")
    suspend fun deletecartitemsofcustomer(cartId: Int)



}