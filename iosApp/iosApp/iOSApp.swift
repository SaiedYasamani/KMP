import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        #if DEBUG
        NapierHelper().initialNapier()
        #endif
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
