package com.marwa.modernecommerceapp.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marwa.modernecommerceapp.R
import com.marwa.modernecommerceapp.databinding.FragmentOnboardingBinding
import com.marwa.modernecommerceapp.domain.entity.OnboardingData
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {
    private val viewModel by viewModel<OnboardingViewModel>()
    private var pagePosition = 0;
    private lateinit var binding: FragmentOnboardingBinding
    private val onboardingList = listOf<OnboardingData>(
        OnboardingData(
            R.string.onboarding_title_1,
            R.string.onboarding_description_1,
            R.drawable.fashion_shop_1,
            R.drawable.circle

        ),
        OnboardingData(
            R.string.onboarding_title_2,
            R.string.onboarding_description_2,
            R.drawable.sales_consulting,
            R.drawable.circle1
        ),
        OnboardingData(
            R.string.onboarding_title_3,
            R.string.onboarding_description_3,
            R.drawable.shopping_bag,
            R.drawable.circle2
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()

        binding.btnNext.setOnClickListener {
            viewModel.setFirstUse(false)
            if (pagePosition == 2) {
//                findNavController().navigate(R.id.action_onboarding_navigation_to_home_navigation)
                findNavController().navigate(R.id.action_onboarding_navigation_to_getStarted_navigation                )
            } else {
                pagePosition++
                updateUI()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (pagePosition > 0) {
                pagePosition--
                updateUI()
            }
        }

        binding.btnSkip.setOnClickListener {
            viewModel.setFirstUse(false)
            if (!viewModel.isLoggedIn()) {
                findNavController().navigate(R.id.action_onboarding_navigation_to_login_navigation)
            } else {
                findNavController().navigate(R.id.action_onboarding_navigation_to_home_navigation)
            }
        }


    }

    private fun updateUI() {
        if (pagePosition == 2) binding.btnNext.text =
            getString(R.string.get_started) else binding.btnNext.text = getString(R.string.next)
        if (pagePosition == 0) binding.btnPrev.visibility = View.INVISIBLE
        else binding.btnPrev.visibility = View.VISIBLE
        binding.lbPageNumber.text = "${pagePosition + 1}"
        binding.lbOnboardingTitle.text = getString(onboardingList[pagePosition].title)
        binding.lbOnboardingDescription.text = getString(onboardingList[pagePosition].description)
        binding.ivOnboardingImage.setImageResource(onboardingList[pagePosition].image)
        binding.ivOnboardingIndicator.setImageResource(onboardingList[pagePosition].indicator)
    }

}