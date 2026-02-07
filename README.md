# 🔧 CI Pipeline Lab

![Jenkins](https://img.shields.io/badge/Jenkins-CASC-red)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![Groovy](https://img.shields.io/badge/Groovy-3.x-green)
![YAML](https://img.shields.io/badge/Config-YAML-orange)

Educational project for learning **CI/CD pipeline design** using **Jenkins Configuration as Code (CASC)**, **YAML-driven job generation**, and **Groovy scripting**.

---

## 🎯 Project Goal

**Problem:** How to build scalable CI pipeline for multi-project Android repo (different architectures, build types)?

**Educational Solution:**
1. Define projects in **YAML contract** (human-readable, version-controlled)
2. **Parser (Groovy)** transforms YAML → Runtime Model (JSON)
3. **Job Generator** creates Jenkins jobs from runtime model
4. **Jenkins CASC** configures instance without manual setup

**Why?**
- Learn **Infrastructure as Code** (CASC, YAML)
- Practice **Groovy scripting** (parser, transformations)
- Understand **Jenkins internals** (job DSL, pipeline syntax)
- Prepare for **DevOps/SRE** path

---

## 📊 Implementation Status

### ✅ Implemented and Working

**YAML Contract:**
- `config/android-projects.yaml` – project definitions with architectures and build_types
- Schema validation (implicit via parser)

**Parser (YAML → JSON):**
- `scripts/generateRuntimeModel.groovy` – YAML extraction to JSON
- `scripts/validateRuntimeModel.groovy` – basic validation of runtime model
- Output: `out/runtime-model.json` (raw YAML dump, no transformation)

**Jenkins Infrastructure:**
- `docker-compose.yml` – Jenkins master in container
- `jenkins/casc-schema.json` – CASC configuration schema
- `jenkins/nodes/` – worker node definitions (TBD: active?)
- Credentials secured (not embedded in YAML)

**Utilities:**
- `runner.sh` – helper script for testing pipeline (TBD: what does it do?)
- `tools/jjb/` – Jenkins Job Builder integration (status UNCLEAR)

### 🔄 In Progress / Partially Implemented

**Job Generation (⚠️ CRITICAL GAP):**
- Parser generates **raw JSON dump** (YAML → JSON 1:1)
- **No transformation** to hierarchical job structure: `{project}/{arch}/{type}`
- **No Jenkinsfile generation** from runtime model
- Commit message (1 Feb 2026): *"Next: implement job generation logic"*

**Jenkins Pipelines:**
- `jenkins/pipelines/` – folder exists, but **unclear** if it contains working Jenkinsfiles
- JJB templates – status unknown (is `tools/jjb/` active?)

**Android Testing:**
- `scripts/setup_device.py` – **EMPTY FILE (0 bytes)** – placeholder
- `scripts/clean_device.py` – **EMPTY FILE (0 bytes)** – placeholder
- Android test execution – **not started**

### ❌ Not Started

**Core Functionality:**
- [ ] Job generation logic (runtime-model.json → Jenkins job structure)
- [ ] Jenkinsfile templates (parametrized for arch/build_type)
- [ ] Pipeline execution (trigger jobs, dependencies)

**Advanced Features:**
- [ ] Multi-arch builds (ARM64, x86_64)
- [ ] Docker agents (ephemeral build environments)
- [ ] Test reporting (JUnit, HTML)
- [ ] Artifact management (APK storage)
- [ ] Notifications (Slack, email)

---

## 🗺️ Roadmap

### Phase 1: Parser & Runtime Model 🔄 (IN PROGRESS)
- [x] YAML contract definition (android-projects.yaml)
- [x] Groovy parser: YAML → JSON extraction
- [x] Runtime model validation (basic)
- [ ] **Transform JSON to hierarchical structure** (BLOCKER)
- [ ] Unit tests for parser logic

### Phase 2: Job Generation (Priority: CRITICAL)
- [ ] Jenkinsfile template engine (Groovy/JJB)
- [ ] Generate jobs from runtime model (`{project}/{arch}/{type}`)
- [ ] Pipeline parametrization (branch, commit SHA)
- [ ] CASC integration (auto-load generated jobs)

### Phase 3: Android CI (Priority: HIGH)
- [ ] Docker agent for Android builds (SDK, emulators)
- [ ] `setup_device.py` implementation (emulator boot)
- [ ] `clean_device.py` implementation (cleanup artifacts)
- [ ] Test execution (instrumented tests, UI tests)

### Phase 4: Multi-Arch & Optimization (Priority: MEDIUM)
- [ ] ARM64 + x86_64 build support
- [ ] Build caching (Gradle cache, Docker layers)
- [ ] Parallel job execution
- [ ] Matrix builds (architectures × build types)

### Phase 5: Production-Ready Features (Priority: LOW)
- [ ] Test reporting dashboard (Allure, TestRail)
- [ ] Security scanning (static analysis, dependency check)
- [ ] Performance tests (app startup time, memory)
- [ ] Blue-Green deployment simulation

---

## 🧱 Tech Stack

| Component | Purpose |
|-----------|---------|
| **Jenkins CASC** | Infrastructure as Code (no manual config) |
| **Groovy** | Parser scripting, pipeline logic |
| **YAML** | Human-readable project configuration |
| **Docker** | Isolated Jenkins environment |
| **JJB (TBD)** | Job generation (if used) |
| **Python** | Device management scripts (TBD) |

---

## 🗂 Project Structure

```
ci-pipeline-lab/
├── config/
│   └── android-projects.yaml      # YAML contract (projects definition)
├── docker/
│   └── [Jenkins Docker configs]   # Custom Jenkins image (TBD)
├── jenkins/
│   ├── casc-schema.json           # CASC configuration schema
│   ├── nodes/                     # Worker node definitions
│   └── pipelines/                 # Jenkinsfile templates (status UNCLEAR)
├── scripts/
│   ├── generateRuntimeModel.groovy  # YAML → JSON parser
│   ├── validateRuntimeModel.groovy  # JSON validation
│   ├── ParserDebug.groovy           # Debug utilities
│   ├── setup_device.py              # ⚠️ EMPTY (placeholder)
│   └── clean_device.py              # ⚠️ EMPTY (placeholder)
├── tools/
│   └── jjb/                       # Jenkins Job Builder (status UNCLEAR)
├── docker-compose.yml             # Jenkins master + dependencies
├── runner.sh                      # Test runner script (TBD: usage?)
└── out/
    └── runtime-model.json         # Generated runtime model (gitignored)
```

---

## 📚 Learning Objectives

After completing this project, you should be able to:
- [x] Design YAML-based configuration contract
- [x] Write Groovy parser (YAML → JSON)
- [ ] Build job generation logic (IN PROGRESS)
- [ ] Configure Jenkins via CASC (partially)
- [ ] Manage Docker-based CI infrastructure
- [ ] Debug pipeline failures (TBD)
- [ ] Optimize build times (TBD)

---

## ⚙️ Setup

### Requirements
- **Docker** ≥ 20.x + Docker Compose
- **Groovy** ≥ 3.x (for local parser testing)
- **Python** ≥ 3.8 (for device management scripts – TBD)
- **OS:** macOS (ARM/Intel), Linux

### Installation

Clone repository:
```bash
git clone git@github.com:pwalen/ci-pipeline-lab.git
cd ci-pipeline-lab
```

### Running Jenkins (Docker)

Start Jenkins master:
```bash
docker-compose up -d
```

Access Jenkins:
```
http://localhost:8080
```

CASC config auto-loaded from `jenkins/casc-schema.json`.

### Testing Parser (locally)

Generate runtime model:
```bash
groovy scripts/generateRuntimeModel.groovy
```

Validate output:
```bash
groovy scripts/validateRuntimeModel.groovy
```

Check output:
```bash
cat out/runtime-model.json
```

**⚠️ Note:** Parser currently generates **raw JSON dump** (YAML 1:1), not hierarchical job structure.

### Debug Mode

```bash
groovy scripts/ParserDebug.groovy
```

---

## 🔍 Current Parser State (Known Issues)

**What works:**
- [x] YAML file loading (android-projects.yaml)
- [x] JSON serialization (output to `out/runtime-model.json`)
- [x] Basic validation (empty projects, missing fields)

**What does NOT work / TODO:**
- [ ] **Transformation logic** – JSON is flat (no structure `{project}/{arch}/{type}`)
- [ ] **Jenkinsfile generation** – no output .groovy files
- [ ] **CASC integration** – generated jobs are not auto-loaded to Jenkins
- [ ] **Error handling** – parser crashes on invalid YAML (no try-catch)

**Next Steps (Phase 2):**
1. Implement `transformToJobStructure()` in parser
2. Create Jenkinsfile template engine
3. Test end-to-end: YAML → Parser → Jobs → Jenkins UI

---

## 🏭 Architecture (Target Design)

```
┌─────────────────┐
│ android-projects│  (YAML contract)
│     .yaml       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ generateRuntime │  (Groovy parser)
│   Model.groovy  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ runtime-model   │  (JSON intermediate)
│     .json       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Job Generator   │  ⚠️ NOT IMPLEMENTED YET
│  (JJB/Groovy)   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Jenkins Jobs    │  (Declarative Pipelines)
│ (project/arch/  │
│    buildType)   │
└─────────────────┘
```

**Current State:** Stuck at `runtime-model.json` (flat structure). Job Generator does not exist.

---

## 🏷️ License and Attribution

This project uses the **Android Testing Samples** repository developed by the Android Open Source Project.

Original repository: https://github.com/android/testing-samples

Licensed under the Apache License, Version 2.0.  
See: http://www.apache.org/licenses/LICENSE-2.0

---

## 📖 Further Resources

**Related Projects:**
- [swaglabs-puppeteer-ts](https://github.com/pwalen/swaglabs-puppeteer-ts) – E2E test suite (integration target)

**Learning Resources:**
- [Jenkins CASC Docs](https://github.com/jenkinsci/configuration-as-code-plugin)
- [Groovy Documentation](https://groovy-lang.org/documentation.html)
- [Jenkins Job Builder](https://jenkins-job-builder.readthedocs.io/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ❓ FAQ / Known Issues

**Q: Does Parser generate Jenkinsfiles?**  
A: **No.** Currently parser only does YAML → JSON dump. Job generation logic is in roadmap (Phase 2).

**Q: Is Jenkins CASC ready?**  
A: **Partially.** Schema exists, credentials secured, but auto-loading generated jobs doesn't work (because jobs are not generated).

**Q: What does `runner.sh` do?**  
A: **UNCLEAR.** Probably test runner for Android CI, but requires code inspection.

**Q: Why are `setup_device.py` and `clean_device.py` empty?**  
A: They are **placeholders** – Android testing is not yet implemented (Phase 3).

**Q: Can I use this in production?**  
A: **No.** This is an educational project. Parser has no error handling, no tests, no job generation.
