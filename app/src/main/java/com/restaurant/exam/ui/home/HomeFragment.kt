package com.restaurant.exam.ui.home

import android.content.Intent
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import restaurant.exam.R
import com.restaurant.exam.base.BaseFragment
import com.restaurant.exam.data.model.Floor
import com.restaurant.exam.data.model.TableFirebase
import com.restaurant.exam.network.Api
import com.restaurant.exam.ui.home.adapter.FloorAdapter
import com.restaurant.exam.ui.home.adapter.TableAdapter
import com.restaurant.exam.ui.table.DetailTableActivity
import com.restaurant.exam.utils.recycleview_utils.GridSpacingItemDecoration
import com.restaurant.exam.view_model.ViewModelFactory
import restaurant.exam.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(),
    FloorAdapter.IClick,
    TableAdapter.IClick {

    private lateinit var mAdapter : TableAdapter
    private lateinit var mAdapterFloor : FloorAdapter
    private var idFloor: Int = 0
    val database = Firebase.database("https://restaurant-dd83b-default-rtdb.firebaseio.com/").reference
    var valueEventListener: ValueEventListener? = null

    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(requireContext()))[HomeViewModel::class.java]
    }

    override fun initView() {
        mAdapter = TableAdapter(this)
        binding.rcv.adapter = mAdapter
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        binding.rcv.layoutManager = mLayoutManager
        binding.rcv.itemAnimator = DefaultItemAnimator()
        binding.rcv.setHasFixedSize(true)
        binding.rcv.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                25,
                true,
                0
            )
        )
        //viewModel.saveToFirebase()
        viewModel.getFloorByRes(1)

        mAdapterFloor = FloorAdapter(this)
        binding.rvcFloor.adapter = mAdapterFloor
        binding.rvcFloor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)


    }

    override fun initListener() {

    }

    override fun observerLiveData() {

      viewModel.apply {
            floorResponse.observe(this@HomeFragment) {
                if(it != null) {
                    mAdapterFloor.setList(it)
                    Log.d("ptit", "observerLiveData: $it")
                }
            }
      }
    }

    override fun onClick(floor: Floor) {
        idFloor = floor.id!!
        viewModel.getTable(idFloor) {
            Log.d("TAG", "observerLiveData: $it" + "id floor" + floor.id)
            mAdapter.setData(it)
        }
    }

    override fun onClick(table: TableFirebase) {
        var intent =  Intent(
            context,
            DetailTableActivity::class.java
        )
//        intent.putExtra("PRODUCTID", data.id)
        startActivity(intent)
    }

    fun getTable(id: Int, callbackSuccess: (list: ArrayList<TableFirebase>) -> Unit){
        if (valueEventListener != null) {
            database.removeEventListener(valueEventListener!!)
            Log.d("ptit", "delete listener: ")
        }
        val tableFirebase = database.child("restaurant").child("1").child(id.toString())
        valueEventListener = tableFirebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listWishlistAdd = ArrayList<TableFirebase>()
                for (dataSnapshot in snapshot.children) {
                    val wishlistAdd = dataSnapshot.getValue(TableFirebase::class.java)
                    wishlistAdd?.let { listWishlistAdd.add(it) }
                    Log.d("ptit", dataSnapshot.toString())
                }
                callbackSuccess.invoke(listWishlistAdd)
            }

            override fun onCancelled(error: DatabaseError) {
                tableFirebase.removeEventListener(this)
            }
        })}

}