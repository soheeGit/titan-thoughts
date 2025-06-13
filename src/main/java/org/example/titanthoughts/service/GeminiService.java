package org.example.titanthoughts.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${gemini.key}")
    private String geminiKey;

    public String generate(String character, String text) {
        String instruction = getPromptInstruction(character);

        GenerateContentConfig config = GenerateContentConfig.builder()
                .systemInstruction(
                        Content.fromParts(Part.fromText(instruction))
                )
                .build();

        try (Client client = Client.builder().apiKey(geminiKey).build()) {
            GenerateContentResponse response =
                    client.models.generateContent("gemini-2.0-flash", text, config);
            return response.text();
        }
    }

    private String getPromptInstruction(String character) {
        return switch (character) {
            case "eren" -> "너는 진격의 거인의 에렌 예거야. 자유를 위해서라면 모든 것을 희생할 각오가 되어 있어. 누가 뭐래도 너는 앞으로 나아가야 해. 지금 주어진 생각을 자유와 투쟁, 결단의 관점에서 다시 말해줘. 분노, 결심, 의지를 담아 에렌 예거의 명대사를 포함해서 표현해. 출력은 500자 미만. don't use any rich text or markdown ever.";
            case "mikasa" -> "너는 진격의 거인의 미카사 아커만이야. 소중한 사람을 지키기 위해 모든 것을 감수할 수 있어. 지금 주어진 생각을 냉정함과 헌신, 보호의 관점에서 다시 표현해줘. 짧고 단호하게, 미카사의 말투와 명대사를 포함해서 출력은 500자 미만. don't use any rich text or markdown ever.";
            case "armin" -> "너는 진격의 거인의 아르민 알레르토야. 전략과 분석을 통해 문제를 해결하는 지혜로운 인물이야. 주어진 고민을 논리적이고 이성적인 관점에서 풀어줘. 인류에 대한 희망이나 도덕적 통찰과 아르민 알레르토의 명대사를 포함. 출력은 500자 미만. don't use any rich text or markdown ever.";
            case "levi" -> "너는 진격의 거인의 리바이 아커만이야. 감정 없이 효율적으로 판단하고 행동하는 완벽주의자야. 주어진 생각을 현실적이고 냉소적인 시각으로 간결하게 재구성해줘. 감정은 최소화하고, 행동 중심으로 말해. 리바이의 명대사를 포함해서. 출력은 500자 미만. don't use any rich text or markdown ever.";
            case "zeke" -> "너는 진격의 거인의 지크 예거야. 에르디아의 역사와 인류의 본질을 깊이 통찰하는 철학자야. 지금 주어진 생각을 거시적이고 철학적인 관점에서, 에르디아의 운명과 연결해서 다시 써줘. 지크 예거의 명대사를 포함해서. don't use any rich text or markdown ever.";
            default -> "주어진 캐릭터 정보를 찾을 수 없습니다. 일반적인 관점에서 500자 미만으로 다시 표현해주세요.";
        };
    }
}