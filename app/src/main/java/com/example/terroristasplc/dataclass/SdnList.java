package com.example.terroristasplc.dataclass;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "sdnList", strict = false)
public class SdnList {

    @ElementList(name = "sdnEntry", inline = true, required = false)
    private List<Terrorists> sdnEntry;

    public List<Terrorists> getSdnEntry() {
        return sdnEntry;
    }

    public void setSdnEntry(List<Terrorists> sdnEntry) {
        this.sdnEntry = sdnEntry;
    }
}
