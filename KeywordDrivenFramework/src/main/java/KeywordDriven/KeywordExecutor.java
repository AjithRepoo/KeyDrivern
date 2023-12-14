package KeywordDriven;

import com.base.TestBase;

// KeywordExecutor.java
public class KeywordExecutor extends TestBase {

    public static void executeKeywords(String testStep, String locator, String locatorType, String action, String value)  {
        // Trim leading and trailing whitespaces from action
        action = action.trim();

        switch (action.toLowerCase()) {
            case "open_browser":
                initialize();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "close_browser":
                closeBrowser();
                break;
            case "navigate":
                navigate(value);
                break;
            case "input":
                inputText(locator, locatorType, value);
                break;
            case "click":
                clickElement(locator, locatorType);
                break;
            // Add more actions as needed
            default:
                System.out.println("Action not defined: " + action);
        }
    }

    
}
