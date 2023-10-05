package com.ilyaden.wallpaperapplication.data

import android.os.AsyncTask
import com.ilyaden.wallpaperapplication.Config
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.regex.Pattern


object ParseSite {


    class HitAPITask : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String?): String? {
            var connection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            val buffer: StringBuffer

            try {
                val url = URL(params[0])
                connection = url.openConnection() as HttpURLConnection
                connection.connect()

                val stream = connection.inputStream
                reader = BufferedReader(InputStreamReader(stream))
                buffer = StringBuffer()
                var line: String?
                while (true) {
                    line = reader.readLine()
                    if (line == null) {
                        break
                    }
                    buffer.append(line)
                }

                var jsonText = buffer.toString()

                val jsonObj = JSONObject(jsonText)
                val plant_name: String = jsonObj.getString("name")

                return plant_name
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
                try {
                    reader?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return null
        }
    }



     fun parse(name: String, num:Int = 10, page:Int = 1): List<Link> {
        return try {

            val inputStream =
                URL("https://api.unsplash.com/search/photos/?client_id=${Config.clientID}&query=$name&orientation=portrait&per_page=$num&page=$page")
            var connection: HttpURLConnection? = null
            var rd: BufferedReader? = null
            connection = inputStream.openConnection() as HttpURLConnection
            inputStream.openConnection()
            connection.connect()



            val test1 = connection.inputStream
            val test2 = InputStreamReader(test1)
            rd = BufferedReader(test2)
            val linksList = ArrayList<Link>()
            val patternString = "\"urls\":\\s*\\{.*?\\}"
            val pattern = Pattern.compile(patternString)
            val matcher = pattern.matcher(rd.readLine())
            while (matcher.find()) {
                val str = matcher.group()
                val oneLink = Link()
                val images = str.substring(8).split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                for (i in 0..1) {
                    val link = images[i]
                    val inexlink = link
                        .replace("\":\"", "::")
                        .replace("\"", "").split("::".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()




                    if (inexlink[0] == "raw") {
                        oneLink.raw = inexlink[1]

                        println("raw" + inexlink[1])
                    } else if (inexlink[0] == "full") {
                        oneLink.full = inexlink[1]
                        println("full" + inexlink[1])
                    } else {
                        break
                    }
                }
                linksList.add(oneLink)
            }

            //OK
            linksList.distinct()
        } catch (e: MalformedURLException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
     }



}

class Link: Serializable {
    var raw: String = ""
    var full: String = ""
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Link

        if (raw != other.raw) return false
        if (full != other.full) return false

        return true
    }

    override fun hashCode(): Int {
        var result = raw.hashCode()
        result = 31 * result + full.hashCode()
        return result
    }

}
