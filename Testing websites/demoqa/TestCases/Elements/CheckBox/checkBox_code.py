from robot.api.deco import keyword


@keyword("CREATE FOLDER LIST")
def create_folder_list(element):
    if element.endswith(".doc"):
        return element \
            .replace(element[0], element[0].lower(), 1) \
            .replace(" ", "") \
            .replace(".doc", "")
    else:
        return element \
            .lower() \
            .replace(" ", "") \
            .replace(".doc", "")
