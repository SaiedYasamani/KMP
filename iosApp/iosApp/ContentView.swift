import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()
    let info = ShowClientInfo().getInfo(input:nil)

	var body: some View {
        let _ = NapierHelper().printLogLevels()
		Text(info)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
