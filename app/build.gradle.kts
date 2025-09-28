plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.mid_tqc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mid_tqc"
        minSdk = 24
        targetSdk = 34
        versionCode = 2  // 版本升級
        versionName = "1.1.0"  // 版本名稱更新

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // 支援向量圖形
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true  // 啟用代碼混淆
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            
            // 優化選項
            isShrinkResources = true  // 移除未使用的資源
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"  // 調試版本後綴
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    // 支援不同螢幕密度
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // 核心 Android 庫
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core:1.12.0")
    
    // Material Design 組件 (升級版本)
    implementation("com.google.android.material:material:1.11.0")
    
    // CardView 支援
    implementation("androidx.cardview:cardview:1.0.0")
    
    // RecyclerView (未來擴展用)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    // 生命週期組件
    implementation("androidx.lifecycle:lifecycle-runtime:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")
    
    // 測試依賴
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    
    // UI 測試
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
}
