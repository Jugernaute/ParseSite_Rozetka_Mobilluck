package compare.site.controllers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.entity.EnumBrowserVersion;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.methods.SaveProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MethodsForController {

    public WebClient webClientSettings(boolean setCssEnabled, boolean setJavaScriptEnabled,
                                       boolean setThrowExceptionOnScriptError, long waitForBackgroundJavaScript){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(setCssEnabled);
        webClient.getOptions().setJavaScriptEnabled(setJavaScriptEnabled);
        webClient.getOptions().setThrowExceptionOnScriptError(setThrowExceptionOnScriptError);
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
        return webClient;
    }

    public String getSizeOfProductInDb(Map<Long, List<? super ProductAbstract>> longListMap){
        long key = 0;
        Set<Map.Entry<Long, List<? super ProductAbstract>>> entries = longListMap.entrySet();
        for (Map.Entry<Long, List<? super ProductAbstract>> next : entries) {
            key = next.getKey();
        }
        return String.valueOf(key);
    }
}
