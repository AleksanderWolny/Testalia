"""Dodanie produktu do koszyka odbywa się poprzez kliknięcie w przycisk „Dodaj” znajdującego się w polu z danym produktem na liście produktów.

Do koszyka zostanie dodana ilość produktów, wybrana za pomocą strzałek góra dół (pojawiających się po podświetleniu pola edycji) lub poprzez wpisanie z klawiatury w polu edycji ilości produktów.

Łączna ilość produktów w koszyku nie może przekroczyć 100."""

from unittest import TestCase, main
from selenium import webdriver


class AddProduct(TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome('chromedriver')

    def test_add_one_item(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_1')

        okulary_price = driver.find_element_by_tag_name('input')
        okulary_price.clear()
        okulary_price.send_keys('1')

        driver.find_element_by_xpath("//span[button/@class='btn btn-sm']").click()

    def test_add_all_item_once(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_1')

        all_price = driver.find_elements_by_tag_name('input')
        all_add = driver.find_elements_by_xpath("//span[button/@class='btn btn-sm']")

        for x in all_price:
            x.clear()
            x.send_keys('1')

        for y in all_add:
            y.click()

    def test_add_100_item(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_1')

        okulary_price = driver.find_element_by_tag_name('input')
        okulary_price.clear()
        okulary_price.send_keys('100')

        driver.find_element_by_xpath("//span[button/@class='btn btn-sm']").click()

    def test_add_more_than_100(self):
        driver = self.driver
        driver.get('https://buggy-testingcup.pgs-soft.com/task_1')

        okulary_price = driver.find_element_by_tag_name('input')
        okulary_price.clear()
        okulary_price.send_keys('1000')

        driver.find_element_by_xpath("//span[button/@class='btn btn-sm']").click()
        self.assertEqual('Łączna ilość produktów w koszyku nie może przekroczyć 100.',
                         driver.switch_to.alert.text)

        driver.switch_to.alert.accept()

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    main()
