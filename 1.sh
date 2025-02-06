#!/bin/bash

# Удаляем директории .git
find . -type d -name ".git" -prune -exec rm -rf {} +

# Удаляем файлы .gitignore и .gitattributes
find . -type f \( -name ".gitignore" -o -name ".gitattributes" \) -delete

# Удаляем файлы README (различные варианты)
find . -type f \( -iname "README" -o -iname "README.md" -o -iname "README.txt" \) -delete

