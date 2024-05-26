import config from "../config.js";



let map;

async function initMap() {

    //@ts-ignore
    const { Map } = await google.maps.importLibrary("maps");
    
    const myLatLng = { lat: -34.65933197815538, lng:  -58.467978870080444 };
   
    map = new Map(document.getElementById("map"), {
        center: { lat:  -34.57933927427457, lng: -58.47999402840382},
       
        zoom: 11.5,
        mapId:"DEMO_MAP_ID",
    });
 
    const { AdvancedMarkerElement, PinElement } = await google.maps.importLibrary("marker");
 new google.maps.marker.AdvancedMarkerElement({
   
     map,
     position:myLatLng,
     title:"MI PRIMER MARCADOR!!!!",
 } )   ;
    new google.maps.marker.AdvancedMarkerElement({
        map,
        position:{ lat: -34.59854590850615, lng: -58.42014140539084},
         
        title:"MI SEGUNDO MARCADOR!!!!",

    } )   ;
}

function cargarMapa(g) {
    var h, a, k, p = "The Google Maps JavaScript API", c = "google", l = "importLibrary", q = "__ib__", m = document, b = window;
    b = b[c] || (b[c] = {});
    var d = b.maps || (b.maps = {}),
        r = new Set,
        e = new URLSearchParams,
        u = () => h || (h = new Promise(async(f, n) => {
            await (a = m.createElement("script"));
            e.set("libraries", [...r] + "");
            for (k in g) e.set(k.replace(/[A-Z]/g, t => "_" + t[0].toLowerCase()), g[k]);
            e.set("callback", c + ".maps." + q);
            a.src = `https://maps.${c}apis.com/maps/api/js?` + e;
            d[q] = f;
            a.onerror = () => h = n(Error(p + " could not load."));
            a.nonce = m.querySelector("script[nonce]")?.nonce || "";
            m.head.append(a)
        }));
    d[l] ? console.warn(p + " only loads once. Ignoring:", g) : d[l] = (f, ...n) => r.add(f) && u().then(() => d[l](f, ...n))
}

// Llama a la función cargarMapa con los parámetros necesarios
cargarMapa({ key: config.apiKey, v: "weekly" });

initMap();



