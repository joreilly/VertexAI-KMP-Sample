import ComposeApp
import FirebaseVertexAI


class GenerativeModelIOS: ComposeApp.GenerativeModel {
    static let shared = GenerativeModelIOS()
    
    let vertex = VertexAI.vertexAI()
    
    let jsonSchema = Schema.array(
      items: .object(
        properties: [
          "name": .string(),
          "country": .string()
        ]
      )
    )
    
    func generateTextContent(prompt: String) async throws -> String? {
        let model = vertex.generativeModel(
            modelName: "gemini-1.5-flash"
        )

        return try await model.generateContent(prompt).text
    }
    
    
    func generateJsonContent(prompt: String) async throws -> String? {
        let model = vertex.generativeModel(
            modelName: "gemini-1.5-flash",
            generationConfig: GenerationConfig(
                responseMIMEType: "application/json",
                responseSchema: jsonSchema
            )
        )
        
        return try await model.generateContent(prompt).text
    }

}
