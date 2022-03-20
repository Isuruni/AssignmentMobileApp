package com.uni.kelani.myfirstapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.uni.kelani.myfirstapp.api.UserAPIService
import com.uni.kelani.myfirstapp.databinding.FragmentFirstBinding
import com.uni.kelani.myfirstapp.models.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val userAPIService = UserAPIService.create()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            //val user= userAPIService.getUser("2");
            val userId = binding.editTextFirst.editableText
            val user = userAPIService.getUser(userId.toString());

            user.enqueue(object : Callback<Users> {
                override fun onResponse(call: Call<Users>, response: Response<Users>) {

                    val body = response.body()
                    body?.let {
                        Log.i("FirstFragment ", it.name)
                        binding.textViewFirst1.text =
                            "Welcome " + it.name + "\n" + "Here is your User Information"
                        binding.textViewFirst2.text = "UserId :"
                        binding.textViewFirst3.text = "Username :"
                        binding.textViewFirst4.text = "Name :"
                        binding.textViewFirst5.text = "Email :"
                        binding.textViewFirst6.text = "Address :"
                        binding.textViewFirst7.text = it.id.toString()
                        binding.textViewFirst8.text = it.username
                        binding.textViewFirst9.text = it.name
                        binding.textViewFirst10.text = it.email
                        binding.textViewFirst11.text = it.address
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.i("FirstFragment", t.message!!)
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}