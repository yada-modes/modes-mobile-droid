package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.BaseActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.BenefitsViewModel
import kotlinx.android.synthetic.main.activity_guides.*
import java.util.*




class BenefitsActivity : BaseActivity(), Observer {

    var viewModel = BenefitsViewModel()

    override var myPageRefIndex = 2
    var standAlone : Boolean = false

    fun loadBenefitsByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitsListByCategoryFragment())

        ft.commit()
    }

    fun loadCategories(){
        buttonCategories.setBackgroundResource(R.drawable.category_selector_box)
        buttonCategories.setTextColor(Color.WHITE)
        buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        buttonAll.setBackgroundResource(0)
        buttonAll.layoutParams.height = 100
        buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonAll.setTextColor(Color.parseColor("#194867"))

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

        ft.commit()
    }

    fun loadBenefitDetail(){

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitDetailFragment())

        ft.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_benefits)

        this.standAlone = intent?.getBooleanExtra("standAlone", false)!!
        var selectedBenefit : String? = intent?.getStringExtra("benefit")

        if(selectedBenefit != null){
            viewModel.selectedBenefit = selectedBenefit
            loadBenefitDetail()
        }

        else {
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())
            ft.commit()
        }


        buttonAll.setOnClickListener {

            Log.d("degug", "button all clicked")
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))

            buttonAll.setBackgroundResource(R.drawable.category_selector_box)
            buttonAll.setTextColor(Color.WHITE)
            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonCategories.setBackgroundResource(0)
            buttonCategories.layoutParams.height = 100
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonCategories.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsListFragment())

            ft.commit()




        }

        buttonCategories.setOnClickListener {
            Log.d("degug", "button categories clicked")

            buttonCategories.setBackgroundResource(R.drawable.category_selector_box)
            buttonCategories.setTextColor(Color.WHITE)
            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonAll.setBackgroundResource(0)
            buttonAll.layoutParams.height = 100
            buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonAll.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

            ft.commit()

        }


    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)

    }

    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_benefits);

        if(viewModel.selectedBenefit == null || viewModel.selectedBenefit.length == 0) {
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())
            ft.commit()
        }

    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is BenefitsViewModel -> {
                if (arg is Boolean){

                    //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}