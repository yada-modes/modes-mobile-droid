package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.fragment_user_settings_description.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserSettingsDescription.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsDescriptionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        print("debug")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_user_settings_description, container, false)


        view.button_serviceMember.setOnClickListener { view ->
            Log.d("usersetup", "Member")

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage2()
        }

        view.button_family.setOnClickListener { view ->
            Log.d("usersetup", "Family Member")
            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage2()
        }

        view.button_spouse.setOnClickListener { view ->
            Log.d("usersetup", "Spouse")
            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage2()
        }


        view.button_page2.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage2()
        }

        view.button_page3.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage3()
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
         * @return A new instance of fragment UserSettingsDescription.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserSettingsDescriptionFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}