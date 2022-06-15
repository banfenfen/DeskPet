package org.taibai.hellohei.items;

import org.taibai.hellohei.items.Shop.ShopEnum;
import org.taibai.hellohei.items.Shop.Shopitem;
import org.taibai.hellohei.items.bath.BathEnum;
import org.taibai.hellohei.items.bath.BathItem;
import org.taibai.hellohei.items.drug.DrugEnum;
import org.taibai.hellohei.items.drug.DrugItem;
import org.taibai.hellohei.items.food.FoodEnum;
import org.taibai.hellohei.items.food.FoodItem;
import org.taibai.hellohei.items.work.WorkEnum;
import org.taibai.hellohei.items.work.WorkItem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>Description: Item存量</p>
 *
 * @author 太白
 */
public class ItemWarehouse {

    private static ItemWarehouse itemWarehouse;

    private final Map<String, FoodItem> foodItemMap = new HashMap<>();
    private final Map<String, BathItem> bathItemMap = new HashMap<>();
    private final Map<String, DrugItem> drugItemMap = new HashMap<>();
    private final Map<String, Shopitem> shopitemMap = new HashMap<>();
    private final Map<String, WorkItem> workItemMap = new HashMap<>();

    private ItemWarehouse() {
        // 这里默认有10个，等后端系统写好后就可以持久化了
        for (FoodEnum foodEnum : FoodEnum.values()) {
            foodItemMap.put(foodEnum.getId(), new FoodItem(foodEnum, 10));
        }
        // 这里也默认有10个洗澡用品
        for (BathEnum bathEnum : BathEnum.values()) {
            bathItemMap.put(bathEnum.getId(), new BathItem(bathEnum, 10));
        }
        for (DrugEnum drugEnum : DrugEnum.values()) {
            drugItemMap.put(drugEnum.getId(), new DrugItem(drugEnum, 10));
        }
        //  默认商店里有20个物品
        for (ShopEnum shopEnum : ShopEnum.values()) {
            shopitemMap.put(shopEnum.getId(), new Shopitem(shopEnum, 20,10) );
        }

        //  默认有10个工作
        for (WorkEnum workEnum : WorkEnum.values()) {
            workItemMap.put(workEnum.getId(),new WorkItem(workEnum,10));
        }
    }

    public static ItemWarehouse getInstance() {
        if (itemWarehouse == null) itemWarehouse = new ItemWarehouse();
        return itemWarehouse;
    }

    public Map<String, FoodItem> getFoodItemMap() {
        return foodItemMap;
    }

    public Map<String, BathItem> getBathItemMap() {
        return bathItemMap;
    }

    public Map<String, DrugItem> getDrugItemMap(){
            return drugItemMap;
        }

    public Map<String, Shopitem> getShopitemMap() {
        return shopitemMap;
    }

    public Map<String, WorkItem> getWorkItemMap() {
        return workItemMap;
    }
}
