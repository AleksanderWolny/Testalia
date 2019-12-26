"""Po najechaniu kursorem myszy na ikonkę menu rozwija się ono, ukazując dodatkowe opcje formularza.

Możliwe jest przejście do trybu edycji formularza poprzez wybranie odpowiedniej opcji z menu.

Edycja formularza umożliwia zmianę każdego z pól:

Imię, Nazwisko, Notka, Telefon
oraz wgranie pliku ze zdjęciem z dysku twardego. Plik ten powinien wyświetlić się w formularzu po prawej stronie po jego zapisie.

Do czasu przejścia w tryb edycji nie jest możliwa zmiana którejkolwiek z wartości w formularzu lub zmiana zdjęcia.

Po zapisie formularza pojawia się wiadomość o udanym zapisie. Dane zostają zmienione i pozostają wyświetlone w formularzu.

Inne funkcje (Strona główna, cofnij) nie zostały zaimplementowane i nie podlegają testom."""
from unittest import TestCase, main
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time


class Filter_product(TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome('chromedriver')

    def test_hover_over_menu(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_3')

        menu_hover = driver.find_element_by_xpath("//li[@class='dropdown']/a[@data-toggle='dropdown']")
        action_hover = ActionChains(driver).move_to_element(menu_hover)
        action_hover.perform()

        formularz_text = driver.find_element_by_xpath("//ul[@id='menu1']/li/a").text
        self.assertEqual(formularz_text, 'Formularz')

        formularz_hover = driver.find_element_by_xpath("//ul[@id='menu1']/li/a")
        action_hover = ActionChains(driver).move_to_element(formularz_hover)
        action_hover.perform()

        edycja_text = driver.find_element_by_xpath("//ul[@id='menu1']/li[1]/ul/li[1]/a").text
        self.assertEqual(edycja_text, 'Przejdź do trybu edycji')

    def test_edit_sheet(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_3')

        menu_hover = driver.find_element_by_xpath("//li[@class='dropdown']/a[@data-toggle='dropdown']")
        action_hover = ActionChains(driver).move_to_element(menu_hover)
        action_hover.perform()

        formularz_hover = driver.find_element_by_xpath("//ul[@id='menu1']/li/a")
        action_hover = ActionChains(driver).move_to_element(formularz_hover)
        action_hover.perform()

        driver.find_element_by_xpath("//ul[@id='menu1']/li[1]/ul/li[1]/a").click()

        driver.find_element_by_class_name("container").click()
        t1 = driver.find_element_by_xpath("//input[@id='in-name']")
        t1.clear()
        t1.send_keys('Adam')
        t2 = driver.find_element_by_id("in-surname")
        t2.clear()
        t2.send_keys('Mik')
        t3 = driver.find_element_by_id("in-notes")
        t3.clear()
        t3.send_keys('test123')
        t4 = driver.find_element_by_id("in-phone")
        t4.clear()
        t4.send_keys('+0123456789')

    # def test_edit_locked_sheet(self):
    #     pass
    #
    # def test_successful_save(self):
    #     pass

    def tearDown(self):
        time.sleep(5)
        self.driver.close()


if __name__ == "__main__":
    main()
