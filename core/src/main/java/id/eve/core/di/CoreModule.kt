package id.eve.core.di

import androidx.room.Room
import id.eve.core.BuildConfig
import id.eve.core.data.UserRepositoryImpl
import id.eve.core.data.local.LocalDataSource
import id.eve.core.data.local.room.UserDatabase
import id.eve.core.data.remote.RemoteDataSource
import id.eve.core.data.remote.network.ApiService
import id.eve.core.domain.repository.UserRepository
import id.eve.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<UserDatabase>().userDao()
    }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("eve".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.github.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/GyhWVHsOXNZc6tGTNd15kXF9YD0kEZaGxYn6MUva5jY=")
            .add(hostname, "sha256/Wec45nQiFwKvHtuHxSAMGkt19k+uPSw9JlEkxhvYPHk=")
            .add(hostname, "sha256/lmo8/KPXoMsxI+J9hY+ibNm2r0IYChmOsF9BxD74PVc=")
            .build()
        val interceptor =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.MY_TOKEN)
                    .header("Content-Type", "application/json")
                chain.proceed(builder.build())
            }
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
}

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get(), get(), get())
    }
}