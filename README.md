MetroGuard — Sistema de Detecció d’Evasió de Tarifes

Un sistema de visió integrat que detecta l’evasió de tarifes en temps real i alerta instantàniament el personal de seguretat.

![imatge alt]
📋 Taula de continguts
Descripció general
Funcionament
Arquitectura del sistema
Requisits de maquinari
Requisits de programari
Instal·lació
Configuració
Sistema d’alertes
Estructura del projecte
Full de ruta
Contribucions
Llicència
Descripció general

MetroGuard és un sistema integrat basat en un microcontrolador compatible amb Arduino i un mòdul de càmera que supervisa en temps real les portes d’entrada del metro. Quan es detecta una persona travessant sense validar el bitllet (per exemple, saltant el torniquet o entrant darrere d’una altra persona), el sistema envia una alerta immediata a la sala de control de seguretat.

L’objectiu és reduir les pèrdues per evasió de tarifes i millorar els temps de resposta sense necessitat de vigilància manual constant.

<!-- FOTO: Primer pla d’algú saltant o passant per sota d’un torniquet del metro — idealment una foto pública o d’estoc que mostri clarament el comportament d’evasió. -->
Funcionament
La càmera captura contínuament la zona del torniquet amb baixa latència.
La detecció local analitza el flux de vídeo per identificar si una persona ha passat amb o sense una activació vàlida de la porta (per exemple, sense so de validació o sense moviment de barrera abans del pas).
Es confirma un esdeveniment d’evasió → el sistema empaqueta una marca temporal, l’ID de la porta i una captura d’imatge.
L’alerta s’envia al panell de control de seguretat en temps real.
El personal de seguretat pot revisar la captura i enviar una resposta.
<!-- FOTO: Diagrama o infografia mostrant el flux: Càmera → Detecció → Alerta → Seguretat. També pot servir un esquema dibuixat a mà. -->
Arquitectura del sistema
┌─────────────────────────────────────────────────────────┐
│                     DISPOSITIU EDGE                    │
│                                                         │
│   [ Mòdul de càmera ] ──► [ Arduino / MCU ]             │
│                               │                         │
│                        Lògica de detecció               │
│                    (visió + fusió de sensors)           │
└───────────────────────────────┼─────────────────────────┘
                                │ WiFi / MQTT / HTTP
                                ▼
               ┌────────────────────────────┐
               │     BACKEND / BROKER       │
               │ (Broker MQTT o API REST)   │
               └────────┬───────────────────┘
                        │
           ┌────────────┴────────────┐
           ▼                         ▼
 ┌────────────────┐      ┌────────────────────────┐
 │ Panell de      │      │ Aplicació mòbil        │
 │ control de     │      │ (si s’utilitza         │
 │ seguretat      │      │ internament pel        │
 │ (Dashboard)    │      │ personal de seguretat) │
 └────────────────┘      └────────────────────────┘
Nota sobre l’aplicació mòbil

Que les alertes també s’enviïn a una aplicació mòbil dependrà de les eines internes que ja utilitzi l’equip de seguretat del metro. Si ja existeix una aplicació mòbil de coordinació, MetroGuard es pot integrar amb ella mitjançant el mateix broker backend. Aquesta integració es considera opcional.

Requisits de maquinari
Component	Descripció
Arduino (ESP32-CAM o similar)	Microcontrolador amb càmera i WiFi integrats
Mòdul de càmera	OV2640 o equivalent
Sensor IR / proximitat (opcional)	Ajuda a detectar el pas de persones
Font d’alimentació	5V regulats (USB o muntatge en rail)
Carcassa	Carcassa resistent per a instal·lació en estacions
Suport de muntatge	Per a instal·lació fixa sobre o al costat del torniquet
<!-- FOTO: La placa Arduino específica utilitzada (per exemple, ESP32-CAM). Foto neta sobre una taula o banc de treball. --> <!-- FOTO: El maquinari muntat i connectat — prototip sobre breadboard o versió final instal·lada. -->
Requisits de programari
Arduino IDE v2.x o superior
Paquet de suport per a plaques ESP32
Llibreries:
esp32cam — interfície de càmera
PubSubClient — missatgeria MQTT
ArduinoJson — serialització de dades
WiFi.h — connectivitat de xarxa
Instal·lació
1. Clonar el repositori
git clone https://github.com/your-username/metroguard.git
cd metroguard
2. Instal·lar dependències

Obre Arduino IDE, ves al gestor de llibreries i instal·la:

PubSubClient per Nick O'Leary
ArduinoJson per Benoit Blanchon
3. Configurar credencials

Copia el fitxer de configuració d’exemple i completa els valors:

cp src/config.example.h src/config.h

Edita src/config.h:

#define WIFI_SSID       "la_teva_xarxa_wifi"
#define WIFI_PASSWORD   "la_teva_contrasenya_wifi"
#define MQTT_BROKER     "192.168.x.x"
#define MQTT_PORT       1883
#define GATE_ID         "gate_01"
4. Carregar el firmware

Selecciona la teva placa (ESP32 Dev Module o AI Thinker ESP32-CAM) a Arduino IDE i prem Upload.

Configuració
Paràmetre	Valor per defecte	Descripció
DETECTION_SENSITIVITY	0.75	Llindar de confiança de detecció
SNAPSHOT_RESOLUTION	FRAMESIZE_VGA	Resolució de la imatge enviada
ALERT_COOLDOWN_MS	3000	Temps mínim entre alertes consecutives
GATE_ID	"gate_01"	Identificador de la porta
Sistema d’alertes

Quan es detecta una evasió de tarifa, el sistema publica un missatge MQTT al tema:

metroguard/alerts/{GATE_ID}
Exemple de payload JSON
{
  "gate_id": "gate_01",
  "timestamp": "2025-10-14T08:42:11Z",
  "confidence": 0.91,
  "snapshot_url": "http://192.168.1.50/snapshots/gate_01_20251014084211.jpg",
  "event_type": "fare_evasion"
}

El panell de control de seguretat es subscriu a tots els temes metroguard/alerts/# i mostra els esdeveniments entrants amb la captura, la ubicació de la porta i la marca temporal en un dashboard en temps real.

Si l’equip de seguretat utilitza una aplicació interna de coordinació mòbil, aquesta també es pot connectar al mateix broker MQTT per enviar notificacions instantànies al personal de camp.

Estructura del projecte
metroguard/
├── src/
│   ├── main.ino
│   ├── detection.h/.cpp
│   ├── camera.h/.cpp
│   ├── comms.h/.cpp
│   └── config.example.h
├── dashboard/
├── assets/
├── docs/
│   └── wiring-diagram.pdf
├── .gitignore
└── README.md
Full de ruta
 Captura de càmera i detecció bàsica de moviment
 Enviament d’alertes MQTT
 Model entrenat per millorar la classificació entre evasió i pas normal
 Suport per múltiples portes i dashboard centralitzat
 Registre d’alertes i anàlisi històrica
 Integració amb aplicacions de seguretat existents
 Gestió de casos especials (persones amb cadira de rodes, personal de manteniment, etc.)
Contribucions

Les pull requests són benvingudes. Per a canvis importants, obriu primer una issue per discutir què voleu modificar.

Feu un fork del repositori
Creeu una branca:
git checkout -b feature/la-meva-funcionalitat
Feu commit dels canvis:
git commit -m 'Afegida una nova funcionalitat'
Pugeu la branca:
git push origin feature/la-meva-funcionalitat
Obriu una Pull Request
Llicència

MIT

<!-- FOTO: Imatge final del dispositiu instal·lat prop d’un torniquet o demostració del sistema en funcionament. -->
