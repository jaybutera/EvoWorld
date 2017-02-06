from plant import Plant
from random import random

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

#records = {} # Store plant id and fitness score
#iteration = 0

def rand():
    r = random() * .2
    if r <= 0.:
        r = .05

    return r

def epoch (seed):
    plants = [Plant(i, seed.atp + rand(), seed.glucose + rand(), pr, sr, fr) for i in range(100)]

    while True:
        # Simulate a timestep and determine which plants died
        plant_stat = []
        for p in plants:
            plant_stat.append( p.step(water, light, co2, oxygen) )

        # Weed out dead plants
        '''
        dead_plants = [p for p, s in zip(plants, plant_stat) if s == False]
        for p in dead_plants:
            records[p.id] = iteration
        '''
        plants_next = [p for p, s in zip(plants, plant_stat) if s == True]

        print '{0} plants left'.format(len(plants))

        # Quit loop if all plants are dead
        if not plants_next:
            return plants # Return longest surviving plants

        # Housekeeping
        plants = plants_next
        #iteration += 1


fit_plant = Plant(0, 1. + rand(), 1. + rand(), pr, sr, fr)
for i in range(100):
    print 'Epoch {0}'.format(i)
    fit_plant = epoch(fit_plant)[0]
