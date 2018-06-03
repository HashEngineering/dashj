package org.bitcoinj.utils;

import org.bitcoinj.core.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Map like container that keeps the N most recently added items
 */
//C++ TO JAVA CONVERTER TODO TASK: C++ template specifiers containing defaults cannot be converted to Java:
//ORIGINAL LINE: template<typename K, typename V, typename Size = uint>
public class CacheMap<K, V> extends ChildMessage {

    private long nMaxSize;

    private long nCurrentSize;

    private LinkedList<CacheItem<K,V>> listItems = new LinkedList<CacheItem<K,V>>();

    private LinkedHashMap<K, CacheItem<K,V>> mapIndex = new LinkedHashMap<K, CacheItem<K,V>>();

    public CacheMap() {
            this(0);
            }
    //C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
    //ORIGINAL LINE: CacheMap(Size nMaxSizeIn = 0) : nMaxSize(nMaxSizeIn), nCurrentSize(0), listItems(), mapIndex()
    public CacheMap(long nMaxSizeIn) {
        this.nMaxSize = nMaxSizeIn;
        this.nCurrentSize = 0;
        this.listItems = new LinkedList<CacheItem<K, V>>();
        this.mapIndex = new LinkedHashMap<K, CacheItem<K, V>>();
    }

    public CacheMap(CacheMap<K, V> other) {
        this.nMaxSize = other.nMaxSize;
        this.nCurrentSize = other.nCurrentSize;
        this.listItems = new LinkedList<CacheItem<K, V>>(other.listItems);
        this.mapIndex = new LinkedHashMap<K, CacheItem<K, V>>();
        rebuildIndex();
    }

    public final void clear() {
        mapIndex.clear();
        listItems.clear();
        nCurrentSize = 0;
    }

    public final void setMaxSize(long nMaxSizeIn) {
        nMaxSize = nMaxSizeIn;
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: Size GetMaxSize() const
    public final long getMaxSize() {
        return nMaxSize;
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: Size GetSize() const
    public final long getSize() {
        return nCurrentSize;
    }

    public final void insert(K key, V value) {
        CacheItem<K, V> it = mapIndex.get(key);
        //C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
        if (it != null) {
            //C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
            CacheItem<K, V> item = it;
            item.value = value;
            return;
        }
        if (nCurrentSize == nMaxSize) {
            pruneLast();
        }
        CacheItem item = new CacheItem(params, key, value);
        listItems.addFirst(item);
        mapIndex.put(key, item);
        ++nCurrentSize;
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: boolean HasKey(const K& key) const
    public final boolean hasKey(K key) {
        return mapIndex.containsKey(key);
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: boolean Get(const K& key, V& value) const
    public final CacheItem<K, V> get(K key) {
        CacheItem<K, V> it = mapIndex.get(key);
        if (it == null) {
            return null;
        }

        return it;
    }

    public final void erase(K key) {
        CacheItem<K, V> it = mapIndex.get(key);
        if (it == null) {
            return;
        }
        //C++ TO JAVA CONVERTER TODO TASK: There is no direct equivalent to the STL list 'erase' method in Java:
        //C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
        listItems.remove(it);
        mapIndex.remove(it.key);
        --nCurrentSize;
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: const ClassicLinkedList<CacheItem<K,V>>& GetItemList() const
    public final LinkedList<CacheItem<K, V>> getItemList() {
        return listItems;
    }

    //C++ TO JAVA CONVERTER NOTE: This 'copyFrom' method was converted from the original copy assignment operator:
    //ORIGINAL LINE: CacheMap<K,V>& operator =(const CacheMap<K,V>& other)
    public final CacheMap<K, V> copyFrom(CacheMap<K, V> other) {
        nMaxSize = other.nMaxSize;
        nCurrentSize = other.nCurrentSize;
        listItems = new LinkedList<CacheItem<K, V>>(other.listItems);
        rebuildIndex();
        return this;
    }


    private void pruneLast() {
        if (nCurrentSize < 1) {
            return;
        }
        CacheItem<K, V> item = listItems.getLast();
        mapIndex.remove(item.key);
        listItems.removeLast();
        --nCurrentSize;
    }

    private void rebuildIndex() {
        mapIndex.clear();
        for(CacheItem<K, V> item : listItems) {
            mapIndex.put(item.key, item);
        }
    }

    @Override
    protected void parse() throws ProtocolException {
        nMaxSize = readInt64();
        nCurrentSize = readInt64();
        long size = readVarInt();
        mapIndex = new LinkedHashMap<K, CacheItem<K, V>>();
        listItems = new LinkedList<CacheItem<K, V>>();
        for (int i = 0; i < size; ++i) {
            CacheItem<K, V> item = new CacheItem<K, V>(params, payload, cursor);
            cursor += item.getMessageSize();
            listItems.add(item);
        }

        length = cursor - offset;
        rebuildIndex();
    }

    @Override
    protected void bitcoinSerializeToStream(OutputStream stream) throws IOException {
        Utils.int64ToByteStreamLE(nMaxSize, stream);
        Utils.int64ToByteStreamLE(nCurrentSize, stream);
        stream.write(new VarInt(listItems.size()).encode());
        for(CacheItem<K, V> item : listItems) {
            item.bitcoinSerialize(stream);
        }
    }

    public String toString() {
        return "CacheMap("+nCurrentSize+" of {"+nMaxSize+"}}";
    }
}