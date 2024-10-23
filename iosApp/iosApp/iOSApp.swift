import SwiftUI
import ComposeApp
import FirebaseCore


@main
struct iOSApp: App {
    init() {
        FirebaseApp.configure()
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
