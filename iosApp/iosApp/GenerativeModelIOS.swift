import ComposeApp
import FirebaseVertexAI


class GenerativeModelIOS: ComposeApp.GenerativeModel {
    static let shared = GenerativeModelIOS()
    
    let vertex = VertexAI.vertexAI()
    var model: FirebaseVertexAI.GenerativeModel {
        vertex.generativeModel(modelName: "gemini-1.5-flash")
    }
    
    func generateContent(prompt: String) async throws -> String? {
        return try await model.generateContent(prompt).text
    }
}
