# 🚀 Evolutionary Neural Network for Lunar Lander

This project implements an **Evolutionary Algorithm (EA)** in Java to optimise a neural network controller for the Lunar Lander problem.

The algorithm evolves neural network weights using biologically inspired operators such as selection, crossover, and mutation, with the goal of minimising landing error and improving generalisation performance.

---

## 🧠 Features

- Tournament Selection (size 3)
- Uniform Crossover
- Gaussian Mutation
- Elitist Replacement Strategy
- Multiple independent runs for evaluation
- Training vs Test performance analysis

---

## ⚙️ Technologies

- Java
- Object-Oriented Programming
- Evolutionary Computation
- Neural Networks

---

## 📊 Algorithm Overview

Each individual in the population represents a neural network with encoded weights. The evolutionary process follows:

1. Initialise population randomly  
2. Evaluate fitness (Lunar Lander performance)  
3. Select parents (tournament selection)  
4. Apply crossover (uniform)  
5. Apply mutation (Gaussian)  
6. Replace worst individuals (elitism)  
7. Repeat until max evaluations reached  

---

## ▶️ How to Run (Eclipse)

### 1. Clone the Repository
``bash
- git clone https://github.com/YOUR_USERNAME/evolutionary-neural-network-lander.git
- cd evolutionary-neural-network-lander

2. Import into Eclipse
- Open Eclipse
- Click File → Import
- Select Existing Projects into Workspace
- Click Next
- Browse and select the project folder
- Click Finish

3. Set Java Version
- Right-click project → Properties
- Go to Java Compiler
- Set version to Java 8, 11, or 17

4. Run the Program
- Navigate to:- coursework → StartNoGui.java
- Right-click StartNoGui.java
- Click:- Run As → Java Application
