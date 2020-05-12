![banner](logo/banner.png)
![Build badge](https://img.shields.io/github/workflow/status/Iltotore/EntityMetadataAPI/Java%20CI/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9af1fd09f7514581a0c2d900c176d50c)](https://www.codacy.com/manual/Iltotore/ManaDrop?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Iltotore/ManaDrop&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/Iltotore/ManaDrop/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/Iltotore/ManaDrop?targetFile=build.gradle)
![License Badge](https://img.shields.io/github/license/Iltotore/ManaDrop)
![Release badge](https://img.shields.io/github/v/release/Iltotore/ManaDrop?include_prereleases)

A Gradle plugin for Minecraft development.

# Description
ManaDrop is a Gradle plugin designed to help Minecraft developers.
It helps to automatise some tasks and makes projects more portables.

# Features
Here are main features of this plugin. For more details about them, check the [wiki](https://github.com/Iltotore/ManaDrop/wiki/)

## General
- [Dependency and Repository shortcuts](https://github.com/Iltotore/ManaDrop/wiki/General-features#dependency-and-repository-shortcuts)
- [YAML validation](https://github.com/Iltotore/ManaDrop/wiki/General-features#yaml-validation)

## Spigot and Paper
- [Plugin.yml generation](https://github.com/Iltotore/ManaDrop/wiki/Spigot#pluginyml-generation)
- [NMS dependencies management](https://github.com/Iltotore/ManaDrop/wiki/Spigot#nms-support)

## BungeeCord and Waterfall
- [Bungee.yml generation](https://github.com/Iltotore/ManaDrop/wiki/BungeeCord#bungeeyml-generation)

## Vanilla
- [Minecraft 1.15+ deobfuscation (experimental)](https://github.com/Iltotore/ManaDrop/wiki/Vanilla#setup-buildgradle)

# Use in your project
You can use this plugin in your Gradle Build by applying it.
- Using `apply plugin: fr.il_totore.manadrop:version`

- Using the new `plugins` statement
```gradle
plugins {
   id 'fr.il_totore.manadrop' version 'version'
}
```

Check the latest available version [here](https://plugins.gradle.org/plugin/fr.il_totore.manadrop)