package si.lanisnik.restaurantorder.ui.menuitem.list

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_menu_item_details.*
import kotlinx.android.synthetic.main.activity_menu_items_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.constants.ActivityConstants.EXTRA_FOOD_CATEGORY
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.ui.menuitem.list.adapter.MenuitemRecyclerAdapter
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import si.lanisnik.restaurantorder.ui.menuitem.navigator.MenuItemNavigator
import si.lanisnik.restaurantorder.ui.order.navigator.OrderNavigator
import javax.inject.Inject

class MenuItemsListActivity : BaseActivity(), MenuitemRecyclerAdapter.OnMenuItemSelectedListener, LoadingStateView.RetryListener {

    @Inject lateinit var adapter: MenuitemRecyclerAdapter
    @Inject lateinit var viewModelFactory: MenuItemsListViewModelFactory
    @Inject lateinit var menuItemNavigator: MenuItemNavigator
    @Inject lateinit var orderNavigator: OrderNavigator
    private lateinit var viewModel: MenuItemListViewModel

    override fun getContentView(): Int = R.layout.activity_menu_items_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        initRecyclerView()
        menuItemLoadingStateView.retryListener = this
        menuItemsShoppingCartView.setOnClickListener {
            orderNavigator.navigateToShoppingCart(this)
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)

        val foodCategory: FoodCategoryModel = unwrapParcel(EXTRA_FOOD_CATEGORY)
        setToolbarTitle(foodCategory.title)
        viewModel.initialize(foodCategory)
    }

    override fun setupObservers() {
        viewModel.getMenuItems().observe(this, Observer<Resource<List<MenuItemModel>>> {
            it?.let { handleDataState(it.status, it.data) }
        })
        viewModel.getShoppingCartObservable().observe(this, Observer {
            menuItemsShoppingCartView.count = it!!
        })
    }

    override fun onMenuItemSelected(item: MenuItemModel) {
        menuItemNavigator.navigateToDetails(this, item)
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    private fun setToolbarTitle(title: String) {
        setTitle(title)
    }

    private fun initRecyclerView() {
        adapter.listener = this
        menuItemsRecyclerView.adapter = adapter
        menuItemsRecyclerView.enableItemDividers()
    }

    private fun handleDataState(state: ResourceState, data: List<MenuItemModel>?) {
        when (state) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showMenuItems(data)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
        menuItemsShoppingCartView.changeVisibility(state != ResourceState.LOADING)
    }

    private fun showMenuItems(menuItems: List<MenuItemModel>?) {
        if (menuItems.isNotNullAndEmpty()) {
            adapter.items = menuItems!!
            menuItemLoadingStateView.hide()
        } else {
            showLoadingState(LoadingStateView.State.EMPTY)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        menuItemLoadingStateView.state = state
        menuItemLoadingStateView.show()
    }
}
