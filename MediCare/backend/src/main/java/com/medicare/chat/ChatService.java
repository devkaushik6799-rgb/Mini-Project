package com.medicare.chat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
@Service
public class ChatService {
 @Value("${ai.api-url:}") private String apiUrl;
 @Value("${ai.api-key:}") private String apiKey;
 @Value("${ai.model:}") private String model;
 private final WebClient client=WebClient.builder().build();
 private static final String SYSTEM = "You are MediCare's informational website assistant. Help with website navigation, ordering steps, stock display and general non-clinical health literacy. Never diagnose, prescribe, recommend a medicine for symptoms, or advise starting/stopping/changing dosage. For personal medical questions, tell the user to consult a licensed clinician or pharmacist. For emergencies, advise contacting local emergency services. Keep replies concise.";
 public String reply(String message){
  if(apiUrl!=null&&!apiUrl.isBlank()&&apiKey!=null&&!apiKey.isBlank()&&model!=null&&!model.isBlank()){
   try {
    Map<String,Object> body=Map.of("model",model,"messages",List.of(Map.of("role","system","content",SYSTEM),Map.of("role","user","content",message)),"temperature",0.2);
    Map<?,?> result=client.post().uri(apiUrl).contentType(MediaType.APPLICATION_JSON).headers(h->h.setBearerAuth(apiKey)).bodyValue(body).retrieve().bodyToMono(Map.class).block();
    if(result!=null && result.get("choices") instanceof List<?> choices && !choices.isEmpty()){
     Object first=choices.get(0);
     if(first instanceof Map<?,?> choice && choice.get("message") instanceof Map<?,?> msg && msg.get("content")!=null) return msg.get("content").toString();
    }
   } catch(Exception ignored) { /* use safe local fallback if provider is unavailable */ }
  }
  return fallback(message);
 }
 private String fallback(String raw){
  String m=raw.toLowerCase(Locale.ROOT);
  if(m.contains("order")||m.contains("cart")) return "To place a demo order, browse the medicine catalogue, add an eligible non-prescription item to your cart, review the estimated total and select “Place demo order”. Prescription-required products need professional review and cannot be checked out in this prototype.";
  if(m.contains("stock")||m.contains("available")) return "The catalogue displays the stock quantity currently stored in the local demo database. Availability can change after an order is placed.";
  if(m.contains("prescription")) return "Prescription-only medicines require a valid prescription and review by a qualified pharmacist. This educational prototype blocks their demo checkout and does not verify real prescriptions.";
  if(m.contains("hello")||m.contains("hi")) return "Hello! I can explain how to use MediCare, search the catalogue or place a demo order. I cannot provide diagnosis or treatment recommendations.";
  if(m.contains("dose")||m.contains("symptom")||m.contains("pain")||m.contains("fever")||m.contains("medicine for")) return "I can't recommend a medicine, diagnose symptoms or advise a dose. Please speak with a licensed doctor or pharmacist who can consider your medical history. If symptoms are severe or urgent, contact local emergency services.";
  return "I can help with MediCare features such as searching medicines, checking displayed stock and placing a demo order. For personal medical advice, please consult a licensed healthcare professional.";
 }
}
