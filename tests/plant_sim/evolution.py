from plant import Plant
from random import random

# Sorted list of (plant, fitness)
def selection(fits):
    return [ fits[int(random()*5)][0].copy() ]
    #return [ fits[0][0].copy() ]

def mutate(plant):
    pr = random() if random() > .5 else plant.protein_rate
    sr = random() if random() > .5 else plant.starch_rate
    fr = random() if random() > .5 else plant.fat_rate

    return Plant(plant.id, atp, glucose, pr, sr, fr)

def evaluate (plants):
    iteration = 0
    fits = []

    while True:
        plants_next = []

        # Simulate a timestep and determine which plants died
        plant_stat = []
        for p in plants:
            plant_stat.append( p.step(water, light, co2, oxygen) )

        # Weed out dead plants
        for p,s in zip(plants, plant_stat):
            if (s == True):
                plants_next.append(p)
            else: # If dead record plant with fitness
                fits.append( (p, iteration) )

        #print '{0} plants left'.format(len(plants))

        # Quit loop if all plants are dead
        if not plants_next:
            break

        # Housekeeping
        plants = plants_next
        iteration += 1

    fits.reverse()
    return fits

# Parameters
pr = .2
sr = 1.8
fr = 2.2
water = 2.
co2 = .5
oxygen = .8
light = 0.

pop_size = 100

# IC
atp = 1.
glucose = 1.

plants = [Plant(i, atp, glucose, pr, sr, fr) for i in range(pop_size)]

for i in range(10000):
    fits = evaluate(plants)
    parents = selection( fits )
    plants = [mutate(parents[0]) for _ in range(pop_size)]

    if (i % 100 == 0):
        print 'Epoch {0}'.format(i)
        print 'Highest fitness: {0}'.format(fits[0][1])
