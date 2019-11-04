package edu.udacity.java.nano.chatroomstarter;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatroomStarterApplicationTest {

	private WebDriver chromeDriver;

	@BeforeEach
	public void setUp(){
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
	}

	@Test
	public void testLogin(){
		chromeDriver.get("http://localhost:8080");
		System.out.println("Page Title: "+chromeDriver.getTitle());
		assertEquals("Chat Room Login",chromeDriver.getTitle(),"Login page is not loaded");
    }

	@Test
	public void testUserJoin() {

		chromeDriver.get("http://localhost:8080");

		System.out.println("Page Title: " + chromeDriver.getTitle());

		assertEquals("Chat Room Login", chromeDriver.getTitle(),"Login page is not loaded");

		WebElement userElement = chromeDriver.findElement(By.id("username"));
		userElement.sendKeys("Priya");

		chromeDriver.findElement(By.className("submit")).click();

		(new WebDriverWait(chromeDriver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("message-content")).isDisplayed();
			}
		});

		WebElement userMessageElement = chromeDriver.findElement(By.className("message-content"));

		String messageReceived = userMessageElement.getText().trim();

		assertTrue(messageReceived.contains("Connected"));
	}


	@Test
	public void testChat() throws InterruptedException {

		chromeDriver.get("http://localhost:8080");

		System.out.println("Page Title: "+chromeDriver.getTitle());

		assertEquals("Chat Room Login",chromeDriver.getTitle(),"Login page is not loaded");

		WebElement userElement = chromeDriver.findElement(By.id("username"));
		userElement.sendKeys("Priya");

		chromeDriver.findElement(By.className("submit")).click();

		(new WebDriverWait(chromeDriver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("message-content")).isDisplayed();
			}
		});

		WebElement userMessageElement = chromeDriver.findElement(By.className("message-content"));

		String messageReceived = userMessageElement.getText().trim();

		assertTrue(messageReceived.contains("Connected"));

		WebElement messsageElement = chromeDriver.findElement(By.id("msg"));
		messsageElement.sendKeys("Hello");

		chromeDriver.findElement(By.id("send-message")).click();

		Thread.sleep(5000);
		List<WebElement> messageElements = chromeDriver.findElements(By.className("message-content"));

		boolean chatReceived = false;

		for (int i=0; i<messageElements.size();i++){
			System.out.println("Message Received:" + messageElements.get(i).getText());
			if((messageElements.get(i).getText().contains("Hello"))){
				chatReceived = true;
			}
		}

		assertTrue(chatReceived,"Chat message not received");

	}

	@Test
	public void testLeaveChat() throws InterruptedException {

		chromeDriver.get("http://localhost:8080");

		assertEquals("Chat Room Login",chromeDriver.getTitle(),"Login page is not loaded");

		WebElement userElement = chromeDriver.findElement(By.id("username"));
		userElement.sendKeys("Priya");

		chromeDriver.findElement(By.className("submit")).click();

		(new WebDriverWait(chromeDriver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("message-content")).isDisplayed();
			}
		});

		chromeDriver.findElement(By.linkText("exit_to_app")).click();

		assertEquals("Chat Room Login",chromeDriver.getTitle(),"User not able to leave the chat");

	}



	@AfterEach
	public void tearDown(){
		if(chromeDriver!=null) {
			chromeDriver.close();
			chromeDriver.quit();
		}
	}

}
