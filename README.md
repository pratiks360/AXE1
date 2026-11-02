<p align="center">
  <h1 align="center">🛒 AXE1 Promo Calculator</h1>
  <p align="center">
    <strong>Cart-total calculator that applies SKU combo promotions — a Java coding-exercise solution.</strong>
  </p>
  <p align="center">
    <img src="https://img.shields.io/badge/Java-8-ed8b00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/Gradle-build-02303a?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
    <img src="https://img.shields.io/badge/Tests-JUnit_4-25a162?style=for-the-badge" alt="Tests">
    <img src="https://img.shields.io/github/last-commit/pratiks360/AXE1?style=for-the-badge&label=Last+Commit" alt="Last Commit">
  </p>
</p>

---

## ✨ What Is This?

**AXE1** prices a basket of SKUs (`A`–`D`) and applies promotion rules (buy-N combos and cross-SKU combos) to produce the cheapest total. It was written as a take-home exercise and is a small, self-contained Java console app.

## 🎯 Pricing Rules

| Rule | Price |
|---|---|
| Single **A** / **B** / **C** / **D** | 50 / 30 / 20 / 15 |
| **3 × A** combo | 130 |
| **2 × B** combo | 45 |
| **C + D** combo | 30 |

Rules live in `PriceListMaster`; each `Promotion` carries a SKU, minimum order eligibility, combo rate and an optional partner SKU.

## 🧩 How It Works

```
┌────────────┐  Product list  ┌──────────────────┐  Promotions  ┌──────────────┐
│   App.java │ ─────────────► │ PromoCalculator  │ ───────────► │  Total price │
│  (driver)  │                │      (Impl)      │              └──────────────┘
└────────────┘                └──────────────────┘
```

## 🚀 Quick Start

**Prerequisites:** JDK 8+, Gradle.

```bash
git clone https://github.com/pratiks360/AXE1.git
cd AXE1
gradle build          # compiles and runs CalculatorTest
```

Run `App.main` from your IDE to see the sample baskets priced.

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Java 8 |
| **Build** | Gradle |
| **Tests** | JUnit 4 (`CalculatorTest`) |

---

<p align="center">
  <sub>Built with ❤️ for a coding challenge.</sub>
</p>
