package com.devops.dxc.devops;

import com.devops.dxc.devops.service.IUfService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class DevopsSeleniumTests {

    @Autowired
    private IUfService ufService;

    private static WebDriver driver;
    private double uf = 0;

    @BeforeAll
    public static void setUp() {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void testSinAhorro() throws InterruptedException {
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("0");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("0");
        driver.findElement(By.id("btnSolicitar")).click();
        Thread.sleep(2000);
        assertEquals("Retiro máximo", 0,
                Integer.parseInt(driver.findElement(By.id("dxcId")).getAttribute("value")));
        assertEquals("Impuesto", 0,
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        assertEquals("Saldo", 0,
                Integer.parseInt(driver.findElement(By.id("saldoId")).getAttribute("value")));
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testAhorroMaximo() throws InterruptedException {
        double uf = ufService.validaUf().getValor();

        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("70000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("0");
        driver.findElement(By.id("btnSolicitar")).click();
        Thread.sleep(2000);
        assertEquals("Retiro máximo", (int)(uf * 150),
                Integer.parseInt(driver.findElement(By.id("dxcId")).getAttribute("value")));
        assertEquals("Saldo", (70000000-(int)(uf * 150)),
                Integer.parseInt(driver.findElement(By.id("saldoId")).getAttribute("value")));
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testRetiroUnMillon() throws InterruptedException {
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("2000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("0");
        driver.findElement(By.id("btnSolicitar")).click();
        Thread.sleep(2000);
        assertEquals("Retiro máximo", 1000000,
                Integer.parseInt(driver.findElement(By.id("dxcId")).getAttribute("value")));
        assertEquals("Saldo", (2000000-1000000),
                Integer.parseInt(driver.findElement(By.id("saldoId")).getAttribute("value")));
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testRetiroTotalAhorro() throws InterruptedException {
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("900000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("0");
        driver.findElement(By.id("btnSolicitar")).click();
        Thread.sleep(2000);
        assertEquals("Retiro máximo", 900000,
                Integer.parseInt(driver.findElement(By.id("dxcId")).getAttribute("value")));
        assertEquals("Saldo", 0,
                Integer.parseInt(driver.findElement(By.id("saldoId")).getAttribute("value")));
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testRetiroDiezPorciento() throws InterruptedException {
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("15000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("0");
        driver.findElement(By.id("btnSolicitar")).click();
        Thread.sleep(2000);
        assertEquals("Retiro máximo", 1500000,
                Integer.parseInt(driver.findElement(By.id("dxcId")).getAttribute("value")));
        assertEquals("Saldo", (int)(15000000-(15000000*0.1)),
                Integer.parseInt(driver.findElement(By.id("saldoId")).getAttribute("value")));
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto1() throws InterruptedException {
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("5000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("450000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0 primer rango de sueldos",0,
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto2() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("1200000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.04 segundo rango de sueldos",(int)(0.04 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto3() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("1700000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.08 tercero rango de sueldos", (int)(0.08 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto4() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("2500000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.135 cuarto rango de sueldos", (int)(0.135 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto5() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("4200000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.23 quinto rango de sueldos", (int)(0.23 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto6() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("4700000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.304 sexto rango de sueldos", (int)(0.304 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }

    @Test
    public void testImpuesto7() throws InterruptedException {
        double uf = ufService.validaUf().getValor();
        driver.findElement(By.id("btnReset")).click();
        driver.findElement(By.id("ahorroId")).click();
        driver.findElement(By.id("ahorroId")).sendKeys("50000000");
        driver.findElement(By.id("sueldoId")).click();
        driver.findElement(By.id("sueldoId")).sendKeys("6500000");
        driver.findElement(By.id("btnSolicitar")).click();
        assertEquals("Impuesto 0.35 septimo rango de sueldos", (int)(0.35 * (int)(150*uf)),
                Integer.parseInt(driver.findElement(By.id("impuestoId")).getAttribute("value")));
        Thread.sleep(2000);
        driver.findElement(By.id("btnReset")).click();
    }
}
