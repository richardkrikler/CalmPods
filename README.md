# CalmPods

AirPods companion for the **Mudita Kompakt** E-ink phone.

CalmPods is a fork of [CAPod](https://github.com/d4rken-org/capod) by [darken](https://github.com/d4rken).
All of CAPod's AirPods features are kept; only the look and a few platform bits were changed for E-ink:

* Battery level and charging status for both pods and the case
* Case-open / on-connect popup, shown as an **accessibility overlay** (also on the lock screen, no "draw over apps" permission)
* Noise control (ANC / Transparency / Adaptive), ear detection with auto play/pause, auto-connect
* Pure black/white UI built on [Mudita Mindful Design](https://github.com/mudita/MMD), no ripples or screen transitions
* Kompakt refresh mode set automatically (`meink`, as in [Katapult](https://github.com/richardkrikler/Katapult))
* Everything unlocked, no Google Play services needed

**If CalmPods is useful to you, please [sponsor the CAPod author](https://github.com/sponsors/d4rken)** — the hard work (BLE decoding, the AAP protocol) is theirs.

## Install

Download the APK from [Releases](https://github.com/richardkrikler/CalmPods/releases) and install it via Mudita Center, `adb install`, or track it with Obtainium.

After the first start:
1. Grant the Bluetooth and notification permissions.
2. For the popup: *Settings → Accessibility → CalmPods overlay → On*.

## Build

```sh
./gradlew assembleFossDebug testFossDebugUnitTest
```

The `gplay` flavor is disabled. Upstream is tracked as the `upstream` remote: `git fetch upstream && git merge upstream/main`.

## License

GPL-3.0, like CAPod. See [LICENSE](LICENSE).
