import SwiftUI
import ComposeApp
import FirebaseCore


@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        initialiseKoin(generativeModel: GenerativeModelIOS.shared)
        //Koin_iosKt.doInitKoin(generativeModel: GenerativeModelIOS.shared)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
