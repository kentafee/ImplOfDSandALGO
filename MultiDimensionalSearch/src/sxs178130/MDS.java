/**
 * Starter code for LP3
 *
 * @author
 */

// Change to your net id
package sxs178130;

// If you want to create additional classes, place them in this file as subclasses of MDS

import java.util.*;

public class MDS {
    // Add fields of MDS here
    private Map<Long, Item> idMap;
    private Map<Money, HashSet<Item>> priceMap;
    private Map<Long, TreeSet<Item>> descMap;

    // Constructors
    public MDS() {
        idMap = new TreeMap<>();
        priceMap = new HashMap<>();
        descMap = new HashMap<>();
    }

    /* Public methods of MDS. Do not change their signatures.
       ______________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated.
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(long id, Money price, java.util.List<Long> list) {
        Item oldItem = idMap.get(id);
        Item item;
        if(list.size()==0)
            item = new Item(id, price, oldItem.getDescription());
        else
            item = new Item(id, price, list);

        // if item is not there in inventory

        if (oldItem == null) {
            idMap.put(id, item);
            updatePriceMap(item);
            updateDescriptionMap(item);

            return 1;
        }
        // if item is already there in inventory
        else {

            idMap.put(id,item);
            Money oldItemPrice = oldItem.getPrice();
            Set itemSetOld = priceMap.get(oldItemPrice);
            itemSetOld.remove(oldItem);
            updatePriceMap(item);

            for (Long oldDesc : oldItem.getDescription()) {
                Set itemSetDescOld = descMap.get(oldDesc);
                itemSetDescOld.remove(oldItem);
            }


            updateDescriptionMap(item);


            return 0;
        }

    }

    private void updatePriceMap(Item item)
    {
        Set itemSet = this.priceMap.get(item.getPrice());
        if (itemSet == null) {
            itemSet = new HashSet();
            itemSet.add(item);
            this.priceMap.put(item.getPrice(), (HashSet<Item>) itemSet);
        }
        else
            itemSet.add(item);
    }

    private void updateDescriptionMap(Item item)
    {
        for (Long desc : item.getDescription()) {
            Set descSet = this.descMap.get(desc);
            if (descSet == null) {
                descSet = new TreeSet();
                descSet.add(item);
                this.descMap.put(desc, (TreeSet<Item>) descSet);
            } else {
                descSet.add(item);
            }

        }
    }
    // b. Find(id): return price of item with given id (or 0, if not found).
    public Money find(long id) {

        Item item = idMap.get(id);
        if (item != null)
            return item.getPrice();
        return new Money();
    }

    /*
       c. Delete(id): delete item from storage.  Returns the sum of the
       long ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public long delete(long id) {
        long sum = 0;
        Item item = idMap.get(id);
        if(item != null) {

            idMap.remove(id);
            Set set = priceMap.get(item.price);
            set.remove(item);

            for (Long desc : item.getDescription()) {
                sum += desc;
                set = descMap.get(desc);
                set.remove(item);
            }
        }
        return sum;
    }

    /*
       d. FindMinPrice(n): given a long int, find items whose description
       contains that number (exact match with one of the long ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMinPrice(long n) {
        Set<Item> items = descMap.get(n);

        if(items==null||items.size()==0){
            return new Money();
        }
        else return ((TreeSet<Item>) items).first().price;

    }

    /*
       e. FindMaxPrice(n): given a long int, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public Money findMaxPrice(long n) {
        Set<Item> items = descMap.get(n);
        if(items==null||items.size()==0){
            return new Money();
        }
        else return ((TreeSet<Item>) items).last().price;
    }

    /*
       f. FindPriceRange(n,low,high): given a long int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(long n, Money low, Money high) {
        int count=0;
        TreeSet<Item> items = descMap.get(n);
        if(items == null){
            return count;
        }

        for(Item item : items){
            Money price = item.getPrice();
            if(price.compareTo(high)==0 || price.compareTo(low)==0){
                count++;
            }
            else if(price.compareTo(high)==-1 && price.compareTo(low)==1){
                count++;
            }
        }
        return count;
    }


    /*
       g. PriceHike(l,h,r): increase the price of every product, whose id is
       in the range [l,h] by r%.  Discard any fractional pennies in the new
       prices of items.  Returns the sum of the net increases of the prices.
    */
    public Money priceHike(long l, long h, double rate) {
        double netIncrease = 0;
        NavigableMap<Long, Item> subsetIdMap = ((TreeMap)idMap).subMap(l, true, h, true);
        for(Item item: subsetIdMap.values()) {
            Money temp = item.getPrice();
            double price = Double.parseDouble(temp.toString());
            double increase = price*rate;
            netIncrease+=increase/100;
            price = price + price*(rate/100);
            int decPart = (int)((price-(long) price)*100);
            long dolPart = (long) price;
            temp.setCents(decPart);
            temp.setDollars(dolPart);
        }
        int decPartNet = (int)((netIncrease-(long) netIncrease)*100);
        long dolPartNet = (long) netIncrease;
//        String stringSum = String.valueOf(netIncrease);
        return new Money(dolPartNet+"."+decPartNet);
    }

    /*
      h. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public long removeNames(long id, java.util.List<Long> list) {

        long sum = 0;
        Item item = idMap.get(id);
        if(item == null)
            return sum;

        Set<Long> listSet = new HashSet<>();
        List<Long> itemDesc = item.getDescription();
        for(Long desc : list)
        {
            listSet.add(desc);
            Set descSet = descMap.get(desc);
            if(descSet!=null)
                descSet.remove(item);
        }

        Iterator<Long> itr = itemDesc.listIterator();
        while (itr.hasNext()) {
            Long desc = itr.next();
            if (listSet.contains(desc)) {
                sum += desc;
                itr.remove();
            }
        }

        return sum;
    }

    // Do not modify the Money class in a way that breaks LP3Driver.java
    public static class Money implements Comparable<Money> {
        long d;
        int c;

        public Money() {
            d = 0;
            c = 0;
        }

        public Money(long d, int c) {
            this.d = d;
            this.c = c;
        }

        public Money(String s) {
            String[] part = s.split("\\.");
            int len = part.length;
            if (len < 1) {
                d = 0;
                c = 0;
            } else if (part.length == 1) {
                d = Long.parseLong(s);
                c = 0;
            } else {
                d = Long.parseLong(part[0]);
                c = Integer.parseInt(part[1]);
            }
        }
        public void setDollars(long d) {
            this.d = d;
        }

        public void setCents(int c) {
            this.c = c;
        }

        public long dollars() {
            return d;
        }

        public int cents() {
            return c;
        }

        public int compareTo(Money other) { // Complete this, if needed
            if (this.d > other.d)
                return 1;
            else if (this.d < other.d)
                return -1;
            else {
                if (this.c > other.c)
                    return 1;
                else if (this.c < other.c)
                    return -1;
                else return 0;
            }
        }

        public String toString() {
            if(c<10)
                return d + ".0" + c;
            else return d + "." + c;

        }
    }


    public static class Item implements Comparable<Item>{
        private MDS.Money price;
        private long id;
        private List<Long> description;

        public Item(long id, MDS.Money price, List<Long> description) {
            this.price = price;
            this.id = id;
            this.description = new LinkedList<>();
            for (Long desc : description) {
                this.description.add(desc);
            }

        }

        public MDS.Money getPrice() {
            return price;
        }

        @Override
        public int compareTo(Item item) {
            return price.compareTo(item.price);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Item)) return false;
            Item e = (Item) obj;
            if (e.getId() == this.getId() && (this.getPrice().compareTo(e.getPrice())) == 0) {
                Iterator<Long> itr1 = this.description.listIterator();
                Iterator<Long> itr2 = this.description.listIterator();

                while (itr1.hasNext() && itr2.hasNext()) {
                    if (itr1.next() != itr2.next())
                        return false;
                }

                if (itr1.hasNext() || itr2.hasNext())
                    return false;
                else
                    return true;
            }
            return false;
        }


        @Override
        public int hashCode() {

            return Objects.hash(getPrice(), getId(), getDescription());
        }

        public void setPrice(MDS.Money price) {
            this.price = price;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public List<Long> getDescription() {
            return description;
        }

        public void setDescription(List<Long> description) {
            this.description = description;
        }


    }


}