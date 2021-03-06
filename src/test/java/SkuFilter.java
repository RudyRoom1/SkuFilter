import Enums.BasedUrls;
import Enums.PathSegment;
import Enums.UrlParams;
import skuJsonObject.TableObject;
import urlWork.SkuUrl;
import workWithFile.FileWork;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkuFilter {

    public static void main(String[] args) {
        SkuUrl url = new SkuUrl();
        TableObject tableObject = new TableObject();
        String pathToFile = ".\\src\\main\\resources\\DataFiles\\sku_in_store_is_deliverable1.table";
        ArrayList<String> table = FileWork.readingFile(pathToFile);
        List<String> list = FileWork.getFilteredData(table, "IsOfSourceFilter$");
        List<String> parsedList = url.getListOfSkuWithLenght(list, 50);
        List<String> urlList = url.getCreatedUrl(BasedUrls.BASE_URL_UAT, PathSegment.PRICE_AVAILABILITY, parsedList, UrlParams.BANNER_CTR, UrlParams.IS_KIOSK, UrlParams.LANGUAGE_ENGLISH, UrlParams.STORE_0987);
        List<TableObject> filteredList = url.getHttpRequest(urlList);
        String filteredData = tableObject.getFilteredListByQuantity(filteredList, "0");
        FileWork.writeToFile(filteredData, ".\\src\\main\\resources\\DataFiles\\Filtered_sku.txt");
    }
}
