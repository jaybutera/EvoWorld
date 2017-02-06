class Plant(object):
    def __init__(self, id, atp, glucose, pr, sr, fr):
        self.id = id
        self.atp = atp
        self.glucose = glucose
        self.protein_rate = pr
        self.starch_rate = sr
        self.fat_rate = fr

    # Return false if dead
    def step(self, w,l,c,o):
        self.atp += -self.atp * self.glucose**2 + o - self.protein_rate - self.fat_rate
        self.glucose += self.atp * self.glucose**2 - self.glucose + w + c + l - self.starch_rate

        if self.atp <= 0. or self.glucose <= 0.:
            return False

        return True
