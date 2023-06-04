package com.miempresa.usofragmentosv4.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.miempresa.usofragmentosv4.R
import kotlinx.android.synthetic.main.fragment_operaciones.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOperaciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOperaciones : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operaciones, container, false)

        val txtNumero1 = view.findViewById<EditText>(R.id.txtNumero1)
        val txtNumero2 = view.findViewById<EditText>(R.id.txtNumero2)
        val spAcciones = view.findViewById<Spinner>(R.id.spAcciones)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular)

        val operaciones = arrayOf("Suma", "Resta", "Multiplicación", "División")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, operaciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAcciones.adapter = adapter
        btnCalcular.setOnClickListener {
            val numero1 = txtNumero1.text.toString().toFloat()
            val numero2 = txtNumero2.text.toString().toFloat()
            val operacion = spAcciones.selectedItem.toString()

            val resultado = when (operacion) {
                "Suma" -> numero1 + numero2
                "Resta" -> numero1 - numero2
                "Multiplicación" -> numero1 * numero2
                "División" -> numero1 / numero2
                else -> 0f
            }

            val resultadoFragment = FragmentoResultado()
            val bundle = Bundle()
            bundle.putFloat("resultado", resultado)
            resultadoFragment.arguments = bundle

            val fragmentManager: FragmentManager? = fragmentManager
            val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.contenedor2, resultadoFragment)
            fragmentTransaction?.commit()
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
         * @return A new instance of fragment FragmentOperaciones.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOperaciones().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}