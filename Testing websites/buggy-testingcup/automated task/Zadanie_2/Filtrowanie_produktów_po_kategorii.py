"""Lista dostępnych kategorii wraz z polem wyszukiwania pojawia się po kliknięciu w pole ‘Proszę wybrać kategorie’.

Wyszukiwanie zawiera system podpowiedzi, tj. po wpisaniu kilku znaków wyświetla się kategoria o nazwie, zawierającej wpisaną frazę lub fragment tekstu.

Funkcja podpowiedzi mapuje wpisaną frazę również na polskie znaki, czyli na przykład po wpisaniu „pilka” otrzymalibyśmy podpowiedź „piłka”.

Filtrowanie produktów następuje po wybraniu kategorii. Wyświetlane są tylko te, które należą do danej kategorii."""
from unittest import TestCase, main
from selenium import webdriver
import time

from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.select import Select


class Filter_product(TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome('chromedriver')

    def test_write_category(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_2')

        driver.find_element_by_xpath("//span[span/@class='selection']").click()
        sport_filter = driver.find_element_by_xpath("//input[@class='select2-search__field']")
        sport_filter.clear()
        sport_filter.send_keys('Sport')
        sport_filter.send_keys(Keys.RETURN)

        sport_list = driver.find_elements_by_xpath("div[@id='product-list']/div/div")
        self.assertIsNotNone(sport_list)

    def test_choose_category(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_2')

        driver.find_element_by_xpath("//span[span/@class='selection']").click()
        driver.find_element_by_xpath("//span/ul/li[2]").click()

    def test_polish_words(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_2')

        driver.find_element_by_xpath("//span[span/@class='selection']").click()
        sport_filter = driver.find_element_by_xpath("//input[@class='select2-search__field']")
        sport_filter.clear()
        sport_filter.send_keys('Firma i uslugi')
        sport_filter.send_keys(Keys.RETURN)

        no_results = driver.find_element_by_xpath("//span/ul/li[1]").text
        self.assertEqual("No results found", no_results)

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    main()
