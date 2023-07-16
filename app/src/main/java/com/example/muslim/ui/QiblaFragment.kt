package com.example.muslim.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.muslim.databinding.FragmentQiblaBinding
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class QiblaFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentQiblaBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var locationManager: LocationManager
    private lateinit var qiblaImageView: ImageView

    private var qiblaDirection: Float = 0f

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQiblaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        qiblaImageView = binding.qiblaImage// Replace with the ID of your ImageView
        requestLocationPermission()
    }

    override fun onResume() {
        super.onResume()
        val orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        sensorManager.registerListener(
            this,
            orientationSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ORIENTATION) {
                val azimuth = it.values[0]
                val qiblaAngle = calculateQiblaDirection(azimuth)
                qiblaImageView.rotation = -qiblaAngle
            }
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val qiblaAngle = calculateQiblaDirection(location.latitude, location.longitude)
                qiblaDirection = qiblaAngle
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )
    }

    private fun calculateQiblaDirection(azimuth: Float): Float {
        val northDirection = -azimuth
        return normalizeAngle(northDirection + qiblaDirection)
    }

    private fun calculateQiblaDirection(latitude: Double, longitude: Double): Float {
        val latMecca = 21.4225
        val lngMecca = 39.8262

        val lngDelta = Math.toRadians(lngMecca - longitude)
        val latMeccaRad = Math.toRadians(latMecca)
        val latDelta = Math.toRadians(latitude) - latMeccaRad

        val y = sin(lngDelta) * cos(Math.toRadians(latitude))
        val x = cos(latMeccaRad) * sin(Math.toRadians(latitude)) - sin(latMeccaRad) * cos(
            Math.toRadians(
                latitude
            )
        ) * cos(lngDelta)
        val qiblaDirectionRad = atan2(y, x)
        val qiblaDirectionDeg = Math.toDegrees(qiblaDirectionRad).toFloat()
        return normalizeAngle(qiblaDirectionDeg)
    }

    private fun normalizeAngle(angle: Float): Float {
        var normalizedAngle = angle
        while (normalizedAngle < 0) {
            normalizedAngle += 360f
        }
        return normalizedAngle % 360f
    }
}
