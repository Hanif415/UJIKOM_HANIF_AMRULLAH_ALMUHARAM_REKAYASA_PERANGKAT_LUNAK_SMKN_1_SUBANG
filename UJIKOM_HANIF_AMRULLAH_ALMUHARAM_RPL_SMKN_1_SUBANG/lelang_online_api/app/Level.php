<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Petugas;

class Level extends Model
{
    public function petugas()
    {
        return $this->hasMany(Petugas::class);
    }
}
