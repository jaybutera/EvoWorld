from plant import Plant
from random import random

def rand():
    return abs(random() * 4.)

# Parameters
pr = .2
sr = .1
fr = .2
water = 2.
co2 = .5
oxygen = .8
light = 4.

# IC
atp = .3
glucose = .6

plants = [Plant(rand(), rand(), pr, sr, fr) for i in range(100)]

while True:
    # Simulate a timestep and determine which plants died
    plant_stat = []
    for p in plants:
        plant_stat.append( p.step(water, light, co2, oxygen) )

    # Weed out dead plants
    plants = [p for p, s in zip(plants, plant_stat) if s == True]

    print '{0} plants left'.format(len(plants))

    # Quit loop if all plants are dead
    if not plants:
        break
