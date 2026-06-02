package cursoSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromRestController {

    @GetMapping("/pal/{word}")
    public String Palindrome (@PathVariable String word){
        if(isPalindrom(word)){
            return "La palabra " + word + " es palindormo";
        } else
            return "La palabra " + word + " NO es palindormo";
    }

    public Boolean isPalindrom(String word){
        int wordLength = word.length();
        for (int i = 0; i < wordLength / 2; i++){
            System.out.println("left:");
            System.out.println(i);
            System.out.println(word.charAt(i));

            System.out.println("right:");
            System.out.println(wordLength - i);
            System.out.println(word.charAt(wordLength - i - 1));

            if(word.toLowerCase().charAt(i) != word.toLowerCase().charAt(wordLength - i - 1)){
                return false;
            }
        }
        return true;
    }
}
