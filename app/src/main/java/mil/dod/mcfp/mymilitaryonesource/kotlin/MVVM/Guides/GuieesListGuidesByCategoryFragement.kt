package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_guides_list_guides_by_category_fragement.view.*
import kotlinx.android.synthetic.main.layout_guides_list.view.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.UserSettingsActivity
import mil.dod.mcfp.mymilitaryonesource.R
import java.io.InputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GuieesListGuidesByCategoryFragement.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuieesListGuidesByCategoryFragement : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    var adapter : GuidesListAdapter? = null

    override fun onResume() {
        super.onResume()

        var guidesActivity = activity as guidesActivity
        guidesActivity.viewModel.selectedGuide = ""

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(
            R.layout.fragment_guides_list_guides_by_category_fragement,
            container,
            false
        )

        var guidesActivity = activity as guidesActivity

        view.button_back.setOnClickListener {
            view.button_back.setOnClickListener {

                var transaction = guidesActivity.supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                guidesActivity.supportFragmentManager.beginTransaction().remove(this).commit()

                guidesActivity.viewModel.selectedCategory = ""
                guidesActivity.forceResume()
                //guidesActivity.loadCategories()

            }

        }



        view.category_name.setText(guidesActivity.viewModel.selectedCategory)


        adapter = GuidesListAdapter(
            guidesActivity.applicationContext, guidesActivity.viewModel.getGuiesByCategory(
                guidesActivity.viewModel.selectedCategory
            )
        )

        view.listGuides.adapter = adapter

        view.listGuides.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("debug", "item clicked")
            //guidesActivity.viewModel.selectedGuide = guidesActivity.viewModel.getAllGuides()[i].Guide!!
            guidesActivity.viewModel.selectedGuide = view.guide.text.toString()
            Log.d("called_guide", guidesActivity.viewModel.selectedGuide)



            var transaction = guidesActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            guidesActivity.supportFragmentManager.beginTransaction().remove(this).commit()

            guidesActivity.loadGuideDetail()
        }
        return view


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuieesListGuidesByCategoryFragement.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuieesListGuidesByCategoryFragement()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class GuidesListAdapter : BaseAdapter {
        var guides =  listOf<Guide>()
        var context: Context? = null

        constructor(context: Context, list: List<Guide>) : super() {
            this.context = context
            this.guides = list
        }

        override fun getCount(): Int {
            return guides.size
        }

        override fun getItem(position: Int): Any {
            return guides[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val guide = this.guides[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_guides_list, parent, false)
            item.guide.text = guide.Guide

            try {
                val ims: InputStream = this.context!!.getAssets()
                    .open("small-images/" + guide!!.GuideImage + "-200x200.jpg")
                // load image as Drawable
                // load image as Drawable
                val d =
                    Drawable.createFromStream(ims, null)
                // set image to ImageView
                // set image to ImageView
                item.image.setImageDrawable(d)
                ims.close()
            }
            catch (e: Exception){
                Log.d("debug ", e.localizedMessage)
            }

            return item
        }
    }


}