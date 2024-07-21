- source : https://developer.android.com/tools/adb?hl=fr#wireless-adb-android-11

1. Connectez votre appareil Android et l'ordinateur hôte `adb` à un réseau Wi-Fi commun.

2. Connectez l'appareil à l'ordinateur hôte à l'aide d'un câble USB.

3. Définissez l'appareil cible pour qu'il écoute une connexion TCP/IP sur le port 5555 :
```bash
adb tcpip 5555
```

4. Débranchez le câble USB de l'appareil cible.

5. Recherchez l'adresse IP de l'appareil Android. Par exemple, sur un appareil Nexus, vous pouvez trouver l'adresse IP dans **Paramètres** > **À propos de la tablette** (ou **À propos du téléphone**) > **État** > **Adresse IP**.

6. Connectez-vous à l'appareil à l'aide de son adresse IP :
```bash
adb connect device_ip_address:5555
```

7. Vérifiez que votre ordinateur hôte est connecté à l'appareil cible :
```bash
adb devices
```

*retour attendu :*
```bash
List of devices attached
device_ip_address:5555 device
```


**L'appareil est connecté !**

#### Résolution de problèmes

Si la connexion `adb` à votre appareil est perdue :
- Assurez-vous que votre hôte est toujours connecté au même réseau Wi-Fi que votre appareil Android.
- Reconnectez-vous en exécutant à nouveau l'étape `adb connect`.
- Si cela ne fonctionne pas, réinitialisez votre hôte `adb` :
```bash
adb kill-server
```
