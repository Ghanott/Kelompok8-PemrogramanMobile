package com.example.aplikasiayamgeprek.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiayamgeprek.Adapter.MenuAdapter
import com.example.aplikasiayamgeprek.LoginActivity
import com.example.aplikasiayamgeprek.R
import com.google.android.material.tabs.TabLayout
import com.example.aplikasiayamgeprek.home.MenuDetailButtomSheet


class HomeFragment : Fragment() {

    private lateinit var rvMenu: RecyclerView
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var tabKategori: TabLayout
    private lateinit var edtSearch: EditText
    private lateinit var fullMenuList: List<MenuModel>


    private var currentCategory: String? = null
    private var currentQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMenu = view.findViewById(R.id.rvMenu)
        tabKategori = view.findViewById(R.id.tabKategori)
        edtSearch = view.findViewById(R.id.edtSearch)


        fullMenuList = listOf(
            MenuModel("Ayam Geprek",       "Rp. 10.000", "Makanan",
                R.drawable.ayam_geprek,"1. Ayam Geprek \n2. nasi "),
            MenuModel("Ayam Bakar",       "Rp. 15.000", "Makanan",
                R.drawable.ayam_geprek,"1. Ayam Bakar  \n2. nasi "),
            MenuModel("Ayam Geprek",       "Rp. 10.000", "Makanan",
                R.drawable.ayam_geprek,"1. Ayam Geprek  \n2. nasi "),
            MenuModel("Teh Asli Ngawi",       "Rp. 10.000", "Minuman",
                R.drawable.ayam_geprek,"1. Teh Asli Ngawi "),
            MenuModel("Kulit Ayam",       "Rp. 5.000", "Snacks",
                R.drawable.ayam_geprek,"1. kulit Ayam"),
            MenuModel("Ayam Paker Promo",       "Rp. 20.000", "Promo",
                R.drawable.ayam_geprek,"1. Ayam Geprek (2)  \n2. nasi (2)" +
                        " \n3. Teh Cap Bangkok (2)"),


        )


        val emailUser: String =
            requireActivity().intent.getStringExtra(LoginActivity.KEY_EMAIL).orEmpty()
        val textHallo = view.findViewById<TextView>(R.id.tvHello)
        textHallo.text = "Hallo, $emailUser"


        menuAdapter = MenuAdapter(fullMenuList){ menu ->
            val bottomSheet = MenuDetailButtomSheet.newInstance(menu)
            bottomSheet.show(childFragmentManager, "MenuDetail")
        }
        rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMenu.adapter = menuAdapter


        setupTabsWithCustomView()


        tabKategori.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentCategory = tab?.text?.toString()
                applyFilter()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentQuery = s?.toString().orEmpty()
                applyFilter()
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        applyFilter()
    }

    private fun setupTabsWithCustomView() {
        val titles = listOf("Makanan", "Minuman", "Snacks", "Promo")

        titles.forEach { title ->
            val tab = tabKategori.newTab()
            tab.text = title
            tabKategori.addTab(tab)

            val tabView = layoutInflater.inflate(R.layout.item_tab_kategori, null)
            val tvTitle = tabView.findViewById<TextView>(R.id.tvTabTitle)
            tvTitle.text = title

            tab.customView = tabView
        }
    }


    private fun applyFilter() {
        var filtered = fullMenuList


        val category = currentCategory
        if (!category.isNullOrBlank()) {
            filtered = filtered.filter { menu ->
                menu.category.equals(category, ignoreCase = true)
            }
        }


        if (currentQuery.isNotBlank()) {
            filtered = filtered.filter { menu ->
                menu.name.contains(currentQuery, ignoreCase = true)
            }
        }

        menuAdapter.submitList(filtered)
    }
}
